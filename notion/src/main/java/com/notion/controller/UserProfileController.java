package com.notion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;

import com.notion.service.*;

@Controller
public class UserProfileController {
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
	@Autowired
	InstituteService instituteService;
	
	@Autowired
	QRService qrService;
	
	HttpSession session;
	
	@RequestMapping(value="/user/profile",method=RequestMethod.GET)
	public ModelAndView loadUserProfile(HttpServletRequest request,@ModelAttribute UserProfileVO userProfileVO,RegVO regVO1)
	{
		session=request.getSession();
		regVO1=(RegVO)session.getAttribute("regDetails");
		userProfileVO.setRegVO(regVO1);
		List<InstituteVO> instituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("/user/userProfile","userProfileData",userProfileVO).addObject("instituteLs", instituteList);
	}
	
	@RequestMapping(value="/user/insertUserProfile",method=RequestMethod.POST)
	public String insertUserProfile(HttpServletRequest request,@ModelAttribute("userProfileData") UserProfileVO userProfileVO1,RegVO regVO2)
	{
		session=request.getSession();
		
		String email=(String)session.getAttribute("userMail");
		String qrmd5=this.qrService.createMd5(email);
		userProfileVO1.setUniqueQR(qrmd5);
		this.userProfileService.insertUserProfile(userProfileVO1);
		
		this.qrService.createQRcode(email, qrmd5);
		
		regVO2=(RegVO)session.getAttribute("regDetails");
		regVO2.setProfileStatus("complete");
		this.regService.insertToRegister(regVO2);
		
		this.qrService.sendQRCode(email);
		
		return "redirect:/user/Dashboard";
	}
	
	@RequestMapping(value="/user/editProfile",method=RequestMethod.GET)
	public ModelAndView loadEditUserProfile(HttpServletRequest request,@ModelAttribute UserProfileVO userProfileVO2,RegVO regVO3)
	{
		session=request.getSession();
		regVO3=(RegVO)session.getAttribute("regDetails");
		userProfileVO2.setRegVO(regVO3);
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileService.getUserProfileByReg(userProfileVO2);
		List<InstituteVO> instituteList=this.instituteService.viewInstitutes();
		
		return new ModelAndView("/user/editProfile","editProfileData",profileData.get(0)).addObject("instituteLs", instituteList);
	}
	
	@RequestMapping(value="/user/updateUserProfile",method=RequestMethod.POST)
	public String updateUserProfile(@ModelAttribute("editProfileData")@Valid UserProfileVO userProfileVO3,BindingResult result,RegVO regVO4)
	{	
		this.regService.insertToRegister(userProfileVO3.getRegVO());
		this.userProfileService.insertUserProfile(userProfileVO3);
		
		return "redirect:/user/Dashboard";
	}
	
	@RequestMapping(value="/admin/payments",method=RequestMethod.GET)
	public ModelAndView loadCollectPayment()
	{
		List<UserProfileVO> collectPaymentLs=this.userProfileService.getUserProfile();
		return new ModelAndView("/admin/viewUsers","usersLs",collectPaymentLs);
	}
	
	@RequestMapping(value="/admin/registeredUsers",method=RequestMethod.GET)
	public ModelAndView loadRegisteredUsers()
	{
		List<UserProfileVO> allUsersLs=this.userProfileService.getUserProfile();
		List<UserProfileVO> activeUsersLs=new ArrayList<UserProfileVO>();
		List<UserProfileVO> blockedUsersLs=new ArrayList<UserProfileVO>();
		
		for(UserProfileVO user : allUsersLs)
		{
			if(user.getRegVO().getLoginVO().getEnabled()==1)
			{
				activeUsersLs.add(user);
			}
			else
			{
				blockedUsersLs.add(user);
			}
		}
		return new ModelAndView("/admin/viewRegisteredUsers","activeUsers",activeUsersLs).addObject("blockedUsers", blockedUsersLs);
	}
}
