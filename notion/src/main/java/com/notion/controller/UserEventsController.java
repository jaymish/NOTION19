package com.notion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;
import com.notion.service.*;

@Controller
public class UserEventsController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	UserEventsService userEventsService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	CollectorService collectorService;
	
	@Autowired
	QRService qrService;
	
	HttpSession session;
	
	@RequestMapping(value="/user/selectEvents",method=RequestMethod.GET)
	public ModelAndView loadSelectEvents(HttpServletRequest request,UserEventsVO userEventsVO,UserProfileVO userProfileVO)
	{
		session=request.getSession();
		userProfileVO=(UserProfileVO)session.getAttribute("profileDetails");
		userEventsVO.setUserProfileVO(userProfileVO);
		
		List<EventVO> unselectedEventsList=this.eventService.unselectedEvents(userEventsVO);
		List<EventVO> individualEventsList=new ArrayList<EventVO>();
		List<EventVO> teamEventsList=new ArrayList<EventVO>();
		for(EventVO eventLS : unselectedEventsList)
		{
			if(eventLS.getStatus().equals("active"))
			{
				if(eventLS.getParticipationType().equals("Individual"))
				{
					individualEventsList.add(eventLS);
				}
				else if(eventLS.getParticipationType().equals("Team"))
				{
					teamEventsList.add(eventLS);
				}
			}
		}
		return new ModelAndView("/user/selectEvents").addObject("individualEvents", individualEventsList).addObject("teamEvents", teamEventsList);
	}
	
	@RequestMapping(value="/user/selectedEvent",method=RequestMethod.GET)
	public String insertUserEvents(HttpServletRequest request,@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO1,UserProfileVO userProfileVO1,EventVO eventVO)
	{
		session=request.getSession();
		userProfileVO1=(UserProfileVO)session.getAttribute("profileDetails");
		userEventsVO1.setUserProfileVO(userProfileVO1);
		eventVO.setEventId(selectedEventId);
		userEventsVO1.setEventVO1(eventVO);
		userEventsVO1.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO1);
		
		return "redirect:/user/viewEvents";
	}
	
	@RequestMapping(value="/user/selectTeam",method=RequestMethod.GET)
	public ModelAndView selectTeam(HttpServletRequest request,@RequestParam("selectedEventId") int selectedEventId,EventVO eventVO1)
	{
		session=request.getSession();
		session.setAttribute("selectedEventId",selectedEventId);
		eventVO1.setEventId(selectedEventId);
		List<EventVO> eventList=this.eventService.editEvent(eventVO1);
		int maxMembers=eventList.get(0).getTeamMax();
		int minMembers=eventList.get(0).getTeamMin();
		
		return new ModelAndView("/user/selectTeam","selectTeamData",new UserEventsVO()).addObject("maxMembers",maxMembers).addObject("minMembers",minMembers);
	}
	
	@RequestMapping(value="/user/insertTeamData",method=RequestMethod.POST)
	public String insertTeam(HttpServletRequest request,@ModelAttribute("selectTeamData")@Valid UserEventsVO userEventsVO2,BindingResult result,UserProfileVO userProfileVO2,EventVO eventVO2)
	{
		session=request.getSession();
		userProfileVO2=(UserProfileVO)session.getAttribute("profileDetails");
		userEventsVO2.setUserProfileVO(userProfileVO2);
		int selectedEventId=(Integer)session.getAttribute("selectedEventId");
		eventVO2.setEventId(selectedEventId);
		userEventsVO2.setEventVO1(eventVO2);
		userEventsVO2.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO2);
		
		session.removeAttribute("selectedEventId");
		
		return "redirect:/user/viewEvents";
	}
	
	@RequestMapping(value="/user/viewEvents",method=RequestMethod.GET)
	public ModelAndView viewSelectedEvents(HttpServletRequest request,UserEventsVO userEventsVO3,UserProfileVO userProfileVO3,@RequestParam(value="payment",required=false)String payment)
	{
		session=request.getSession();
		userProfileVO3=(UserProfileVO)session.getAttribute("profileDetails");
		userEventsVO3.setUserProfileVO(userProfileVO3);
		
		List<UserEventsVO> selectedEventsLs=this.userEventsService.viewUserEvents(userEventsVO3);
		List<UserEventsVO> pendingPaymentsLs=new ArrayList<UserEventsVO>();
		List<UserEventsVO> completedPaymentsLs=new ArrayList<UserEventsVO>();
		for(UserEventsVO selectedEvent : selectedEventsLs)
		{
			if(selectedEvent.getPaymentStatus().equals("pending"))
			{
				pendingPaymentsLs.add(selectedEvent);
			}
			else if(selectedEvent.getPaymentStatus().equals("complete"))
			{
				completedPaymentsLs.add(selectedEvent);
			}
		}
		int totalPending=0;
		for(UserEventsVO calcTotalPending : pendingPaymentsLs)
		{
			totalPending+=calcTotalPending.getEventVO1().getEventPrice();
		}
		
		int totalPaid=0;
		for(UserEventsVO calcTotalPaid : completedPaymentsLs)
		{
			totalPaid+=calcTotalPaid.getEventVO1().getEventPrice();
		}
		
		return new ModelAndView("/user/viewSelectedEvents","selectedEventsLs",pendingPaymentsLs).addObject("totalPending", totalPending).addObject("registeredEventsLs", completedPaymentsLs).addObject("totalPaid", totalPaid).addObject("payment",payment);
	}
	
	@RequestMapping(value="/user/removeSelectedEvent",method=RequestMethod.GET)
	public String removeSelectedEvent(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO4)
	{
		userEventsVO4.setUserEventId(selectedEventId);
		
		this.userEventsService.removeUserEvent(userEventsVO4);
		
		return "redirect:/user/viewEvents";
	}
	
	@RequestMapping(value="/admin/removeSelectedEvent",method=RequestMethod.GET)
	public void removeSelectedEvent2(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO5)
	{
		userEventsVO5.setUserEventId(selectedEventId);
		
		this.userEventsService.removeUserEvent(userEventsVO5);
	}
	
	@RequestMapping(value="/admin/removeRegisteredEvent",method=RequestMethod.GET)
	public void removeRegisteredEvent(HttpServletRequest request,@RequestParam("selectedEventId") int selectedEventId,@RequestParam("amount") int amount,UserEventsVO userEventsVO6,CollectorVO collectorVO)
	{
		userEventsVO6.setUserEventId(selectedEventId);
		this.userEventsService.removeUserEvent(userEventsVO6);
		
		session=request.getSession();
		UserProfileVO userProfileVO6=(UserProfileVO)session.getAttribute("userProfile");
		this.userEventsService.collectPayment(userProfileVO6);
		
		String collectorUsername=(String)session.getAttribute("adminMail");
		Date date=new Date();
		String collectiontime=date.toString();
		amount = -amount;
		
		collectorVO.setCollectorUsername(collectorUsername);
		String nameOfUser = userProfileVO6.getRegVO().getFirstname()+" "+userProfileVO6.getRegVO().getLastname();
		collectorVO.setNameOfUser(nameOfUser);
		collectorVO.setTotalAmount(amount);
		collectorVO.setTime(collectiontime);
		this.collectorService.insertCollection(collectorVO);
	}
	
	@RequestMapping(value="/admin/registeredEvents",method=RequestMethod.GET)
	public ModelAndView viewEventRegistrations()
	{
		List<UserEventsVO> registeredEventsLs=this.userEventsService.paymentComplete();
		List<UserEventsVO> selectedEventsLs=this.userEventsService.paymentPending();
		List<UserEventsVO> teamMembersLs=new ArrayList<UserEventsVO>();
		
		for(UserEventsVO regEvent : registeredEventsLs)
		{
			if(regEvent.getEventVO1().getParticipationType().equals("Team"))
			{
				teamMembersLs.add(regEvent);
			}
		}
		
		return new ModelAndView("/admin/viewEventRegistrations","paymentComplete",registeredEventsLs).addObject("teamMembersLs", teamMembersLs).addObject("paymentPending", selectedEventsLs);
	}
	
	@RequestMapping(value="/admin/viewUserEvents",method=RequestMethod.GET)
	public ModelAndView viewUserEvents(HttpServletRequest request,@RequestParam("selectedProfileId") int selectedProfileId,UserProfileVO userProfileVO4,UserEventsVO userEventsVO6)
	{
		userProfileVO4.setProfileId(selectedProfileId);
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileService.getUserProfileById(userProfileVO4);
		session=request.getSession();
		session.setAttribute("userProfile", profileData.get(0));
		userEventsVO6.setUserProfileVO(profileData.get(0));
		
		List<UserEventsVO> selectedEventsLs=this.userEventsService.viewUserEvents(userEventsVO6);
		List<UserEventsVO> pendingPaymentsLs=new ArrayList<UserEventsVO>();
		List<UserEventsVO> completedPaymentsLs=new ArrayList<UserEventsVO>();
		for(UserEventsVO selectedEvent : selectedEventsLs)
		{
			if(selectedEvent.getPaymentStatus().equals("pending"))
			{
				pendingPaymentsLs.add(selectedEvent);
			}
			else if(selectedEvent.getPaymentStatus().equals("complete"))
			{
				completedPaymentsLs.add(selectedEvent);
			}
		}
		int totalPending=0;
		for(UserEventsVO calcTotalPending : pendingPaymentsLs)
		{
			totalPending+=calcTotalPending.getEventVO1().getEventPrice();
		}
		
		int totalPaid=0;
		for(UserEventsVO calcTotalPaid : completedPaymentsLs)
		{
			totalPaid+=calcTotalPaid.getEventVO1().getEventPrice();
		}
		
		return new ModelAndView("/admin/viewUserEvents","selectedEventsLs",pendingPaymentsLs).addObject("totalPending", totalPending).addObject("registeredEventsLs", completedPaymentsLs).addObject("totalPaid", totalPaid);
	}
	
	@RequestMapping(value="/admin/collectPayment",method=RequestMethod.GET)
	public String collectUserPayment(HttpServletRequest request,@RequestParam(value="amount") int amount,CollectorVO collectorVO)
	{
		session=request.getSession();
		UserProfileVO userProfileVO5=(UserProfileVO)session.getAttribute("userProfile");
		this.userEventsService.collectPayment(userProfileVO5);
		
		String collectorUsername=(String)session.getAttribute("adminMail");
		Date date=new Date();
		String collectiontime=date.toString();
		
		collectorVO.setCollectorUsername(collectorUsername);
		String nameOfUser = userProfileVO5.getRegVO().getFirstname()+" "+userProfileVO5.getRegVO().getLastname();
		collectorVO.setNameOfUser(nameOfUser);
		collectorVO.setTotalAmount(amount);
		collectorVO.setTime(collectiontime);
		this.collectorService.insertCollection(collectorVO);
		
		return "redirect:/admin/viewUserEvents?selectedProfileId="+userProfileVO5.getProfileId();
	}
	
	@RequestMapping(value="/admin/collectors",method=RequestMethod.GET)
	public ModelAndView viewCollectors()
	{
		List<CollectorVO> collectorList=this.collectorService.viewCollection();
		int totalCollected=0;
		for(CollectorVO calcTotal : collectorList)
		{
			totalCollected+=calcTotal.getTotalAmount();
		}
		return new ModelAndView("/admin/viewCollectors","collectorLs",collectorList).addObject("totalCollection", totalCollected);
	}
	
	@RequestMapping(value="/admin/viewPresence",method=RequestMethod.GET)
	public ModelAndView loadViewPresence(@RequestParam("type") String participation)
	{
		List<UserEventsVO> presenceList=this.userEventsService.viewPresence();
		List<UserEventsVO> individualPresence=new ArrayList<UserEventsVO>();
		List<UserEventsVO> teamPresence=new ArrayList<UserEventsVO>();
		for(UserEventsVO eventType : presenceList)
		{
			if(eventType.getEventVO1().getParticipationType().equals("Individual"))
			{
				individualPresence.add(eventType);
			}
			else if(eventType.getEventVO1().getParticipationType().equals("Team"))
			{
				teamPresence.add(eventType);
			}
		}
		
		if(participation.equals("individual"))
		{
			return new ModelAndView("/admin/viewIndividualPresence","individualPresenceLs",individualPresence);
		}
		else
		{
			return new ModelAndView("/admin/viewTeamPresence","teamPresenceLs",teamPresence);
		}
	}
	
	@RequestMapping(value="/attendance",method=RequestMethod.POST)
	public void markAttendance(@RequestParam("eventId") int eventId,@RequestParam("qr") String uniqueQR,UserEventsVO userEventsVO7,UserProfileVO userProfileVO6,EventVO eventVO2)
	{
		userProfileVO6.setUniqueQR(uniqueQR);
		List<UserProfileVO> userProfilebyQR = new ArrayList<UserProfileVO>();
		userProfilebyQR=this.userProfileService.getUserProfileByQR(userProfileVO6);
		
		eventVO2.setEventId(eventId);
		List<EventVO> forEventId=new ArrayList<EventVO>();
		forEventId=this.eventService.editEvent(eventVO2);
		
		userEventsVO7.setEventVO1(forEventId.get(0));
		userEventsVO7.setUserProfileVO(userProfilebyQR.get(0));
		int rows=this.userEventsService.markAttendance(userEventsVO7);
		
		if(rows==1)
		{
			System.out.println(rows+": Success");
		}
		else
		{
			System.out.println(rows+": Failure");
		}
	}
}
