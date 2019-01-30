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
	public ModelAndView loadSelectEvents(HttpServletRequest request,UserEventsVO userEventsVO,RegVO regVO)
	{
		session=request.getSession();
		regVO=(RegVO)session.getAttribute("regDetails");
		userEventsVO.setRegVO1(regVO);
		
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
	public String insertUserEvents(HttpServletRequest request,@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO1,RegVO regVO1,EventVO eventVO)
	{
		session=request.getSession();
		regVO1=(RegVO)session.getAttribute("regDetails");
		
		userEventsVO1.setRegVO1(regVO1);
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
	public String insertTeam(HttpServletRequest request,@ModelAttribute("selectTeamData")@Valid UserEventsVO userEventsVO2,BindingResult result,RegVO regVO2,EventVO eventVO2)
	{
		session=request.getSession();
		regVO2=(RegVO)session.getAttribute("regDetails");
		userEventsVO2.setRegVO1(regVO2);
		int selectedEventId=(Integer)session.getAttribute("selectedEventId");
		eventVO2.setEventId(selectedEventId);
		userEventsVO2.setEventVO1(eventVO2);
		userEventsVO2.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO2);
		
		session.removeAttribute("selectedEventId");
		
		return "redirect:/user/viewEvents";
	}
	
	@RequestMapping(value="/user/viewEvents",method=RequestMethod.GET)
	public ModelAndView viewSelectedEvents(HttpServletRequest request,UserEventsVO userEventsVO3,RegVO regVO3)
	{
		session=request.getSession();
		regVO3=(RegVO)session.getAttribute("regDetails");
		userEventsVO3.setRegVO1(regVO3);
		
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
}
