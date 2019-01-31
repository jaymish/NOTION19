package com.notion.controller;
import java.io.IOException;
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
	public ModelAndView viewSelectedEvents(HttpServletRequest request,UserEventsVO userEventsVO3,UserProfileVO userProfileVO3)
	{
		session=request.getSession();
		userProfileVO3=(UserProfileVO)session.getAttribute("profileDetails");
		userEventsVO3.setUserProfileVO(userProfileVO3);
		
		List<UserEventsVO> selectedEventsLs=this.userEventsService.viewUserEvents(userEventsVO3);
		int totalPrice=0;
		for(UserEventsVO calcTotal : selectedEventsLs)
		{
			totalPrice+=calcTotal.getEventVO1().getEventPrice();
		}
		
		return new ModelAndView("/user/viewSelectedEvents","selectedEventsLs",selectedEventsLs).addObject("totalPrice", totalPrice);
	}
	
	@RequestMapping(value="/user/removeSelectedEvent",method=RequestMethod.GET)
	public String removeSelectedEvent(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO4)
	{
		userEventsVO4.setUserEventId(selectedEventId);
		
		this.userEventsService.removeUserEvent(userEventsVO4);
		
		return "redirect:/user/viewEvents";
	}
	
	@RequestMapping(value="/admin/removeSelectedEvent",method=RequestMethod.GET)
	public String removeSelectedEvent2(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO5)
	{
		userEventsVO5.setUserEventId(selectedEventId);
		
		this.userEventsService.removeUserEvent(userEventsVO5);
		
		return "redirect:/admin/collectPayment";
	}
	
	@RequestMapping(value="/admin/registeredEvents",method=RequestMethod.GET)
	public ModelAndView viewEventRegistrations()
	{
		List<UserEventsVO> registeredEventsLs=this.userEventsService.paymentComplete();
		List<UserEventsVO> teamMembersLs=new ArrayList<UserEventsVO>();
		
		for(UserEventsVO regEvent : registeredEventsLs)
		{
			if(regEvent.getEventVO1().getParticipationType().equals("team"))
			{
				teamMembersLs.add(regEvent);
			}
		}
		
		return new ModelAndView("/admin/viewEventRegistrations","paymentComplete",registeredEventsLs).addObject("teamMembersLs", teamMembersLs);
	}
	
	@RequestMapping(value="/admin/collectPayments",method=RequestMethod.GET)
	public ModelAndView loadCollectPayment()
	{
		List<UserEventsVO> collectPaymentLs=this.userEventsService.paymentPending();
		return new ModelAndView("/admin/collectPayments","pendingPaymentsLs",collectPaymentLs);
	}
	
	@RequestMapping(value="/admin/collect",method=RequestMethod.GET)
	public String updatePaymentStatus(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO6)
	{
		userEventsVO6.setUserEventId(selectedEventId);
		
		this.userEventsService.completePayment(userEventsVO6);
		
		return "redirect:/admin/collectPayments";
	}
}
