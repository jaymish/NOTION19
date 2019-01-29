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
	public ModelAndView loadSelectEvents()
	{
		List<EventVO> viewEventsList=this.eventService.viewEvents();
		List<EventVO> individualEventsList=new ArrayList<EventVO>();
		List<EventVO> teamEventsList=new ArrayList<EventVO>();
		for(EventVO eventLS : viewEventsList)
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
	public ModelAndView insertUserEvents(HttpServletRequest request,@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO,LoginVO loginVO4,RegVO regVO1,EventVO eventVO)
	{
		session=request.getSession();
		regVO1=(RegVO)session.getAttribute("regDetails");
		
		userEventsVO.setRegVO1(regVO1);
		eventVO.setEventId(selectedEventId);
		userEventsVO.setEventVO1(eventVO);
		userEventsVO.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO);
		
		return new ModelAndView("redirect:/user/selectEvents");
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
		
		return new ModelAndView("/user/selectTeam","selectTeamData",new UserEventsVO()).addObject("selectedEventId",selectedEventId).addObject("maxMembers",maxMembers).addObject("minMembers",minMembers);
	}
	
	@RequestMapping(value="/user/insertTeamData",method=RequestMethod.POST)
	public ModelAndView insertTeam(HttpServletRequest request,@ModelAttribute("selectTeamData")@Valid UserEventsVO userEventsVO1,BindingResult result,RegVO regVO2,EventVO eventVO2)
	{
		session=request.getSession();
		regVO2=(RegVO)session.getAttribute("regDetails");
		userEventsVO1.setRegVO1(regVO2);
		int selectedEventId=(Integer)session.getAttribute("selectedEventId");
		eventVO2.setEventId(selectedEventId);
		userEventsVO1.setEventVO1(eventVO2);
		userEventsVO1.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO1);
		
		session.removeAttribute("selectedEventId");
		
		return new ModelAndView("redirect:/user/selectEvents");
	}
}
