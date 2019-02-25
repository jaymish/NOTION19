package com.notion.controller;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.CollectorVO;
import com.notion.model.UserProfileVO;
import com.notion.service.*;
import com.paytm.pg.merchant.CheckSumServiceHelper;

@Controller
public class PaytmGatewayController {
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserEventsService userEventsService;
	
	@Autowired
	CollectorService collectorService;
	
	@Autowired
	QRService qrService;
	
	HttpSession session;
	
	@RequestMapping(value="/user/payonline",method=RequestMethod.GET)
	public ModelAndView loadCheckoutDetails(HttpServletRequest request,@RequestParam(value="txn_amount") int txn_amount,UserProfileVO userDetails) throws Exception
	{
		session=request.getSession();
		userDetails = (UserProfileVO)session.getAttribute("profileDetails");
		String emailid = userDetails.getRegVO().getLoginVO().getUsername();
		Date date=new Date();
		String ordertime = date.toString();
		String OrderId = emailid+ordertime;
		String encOrderId=this.qrService.createMd5(OrderId);
		
		
		String merchantMid = "YoOyay48235080472525";
		// Key in your staging and production MID available in your dashboard
		String merchantKey = "ZZx#w@SvQQEISJJw";
		// Key in your staging and production merchant key available in your dashboard
		String orderId = encOrderId;
		String channelId = "WEB";
		String custId = Integer.toString(userDetails.getProfileId());
		String mobileNo = userDetails.getContact();
		String email = emailid;
		String txnAmount = Integer.toString(txn_amount);
		String website = "WEBSTAGING";
		// This is the staging value. Production value is available in your dashboard
		String industryTypeId = "Retail";
		// This is the staging value. Production value is available in your dashboard
		String callbackUrl = "http://192.168.43.210:9090/user/paytmResponse/";
		ModelAndView modelAndView = new ModelAndView("redirect:https://securegw-stage.paytm.in/theia/processTransaction");
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		paytmParams.put("MID",merchantMid);
		paytmParams.put("ORDER_ID",orderId);
		paytmParams.put("CHANNEL_ID",channelId);
		paytmParams.put("CUST_ID",custId);
		paytmParams.put("MOBILE_NO",mobileNo);
		paytmParams.put("EMAIL",email);
		paytmParams.put("TXN_AMOUNT",txnAmount);
		paytmParams.put("WEBSITE",website);
		paytmParams.put("INDUSTRY_TYPE_ID",industryTypeId);
		paytmParams.put("CALLBACK_URL", callbackUrl);
		String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, paytmParams);
		paytmParams.put("CHECKSUMHASH",paytmChecksum);
		modelAndView.addAllObjects(paytmParams);
		return modelAndView;
	}
	
	@RequestMapping(value="/user/paytmResponse",method=RequestMethod.POST)
	public String paytmResponse(HttpServletRequest request,UserProfileVO userProfileVO,CollectorVO collectorVO) throws Exception
	{
		final String merchantKey = "ZZx#w@SvQQEISJJw";
		String paytmChecksum = null;
		// Create a tree map from the form post param
		TreeMap<String, String> paytmParams = new TreeMap<String, String>();
		// Request is HttpServletRequest
		for (Entry<String, String[]> requestParamsEntry : request.getParameterMap().entrySet()) {
		    if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
		        paytmChecksum = requestParamsEntry.getValue()[0];
		    } else {
		        paytmParams.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
		    }
		}
		// Call the method for verification
		boolean isValidChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(merchantKey, paytmParams, paytmChecksum);
		// If isValidChecksum is false, then checksum is not valid
		if(isValidChecksum){
			System.out.println("Checksum Matched");
			session = request.getSession();
			userProfileVO = (UserProfileVO)session.getAttribute("profileDetails");
			 this.userEventsService.collectPayment(userProfileVO);
			
			String txn_date=paytmParams.get("TXNDATE");
			System.out.println(paytmParams.get("TXNAMOUNT"));
			int amount=(int)Float.parseFloat(paytmParams.get("TXNAMOUNT"));
			System.out.println(amount);
			List<UserProfileVO> profileData=this.userProfileService.getUserProfileById(userProfileVO);
			String nameOfUser=profileData.get(0).getRegVO().getFirstname()+" "+profileData.get(0).getRegVO().getLastname();
			collectorVO.setCollectorUsername("PayTM");
			collectorVO.setNameOfUser(nameOfUser);
			collectorVO.setTotalAmount(amount);
			collectorVO.setTime(txn_date);
			this.collectorService.insertCollection(collectorVO);
			
			return "redirect:/user/viewEvents?payment=received";
		}else{
			System.out.println("Checksum MisMatch");
			return "redirect:/user/viewEvents?payment=notreceived";
		}
	}
}
