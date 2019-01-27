package com.notion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
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
	
	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String userName = user.getUsername();
	
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
	public ModelAndView insertUserEvents(@RequestParam("selectedEventId") int selectedEventId,UserEventsVO userEventsVO,LoginVO loginVO4,RegVO regVO1,EventVO eventVO)
	{
		
		loginVO4.setUsername(userName);
		loginVO4=this.loginService.getUser(loginVO4);
		regVO1.setLoginVO(loginVO4);
		regVO1=this.regService.getRegDetails(regVO1);
		
		userEventsVO.setRegVO1(regVO1);
		eventVO.setEventId(selectedEventId);
		userEventsVO.setEventVO1(eventVO);
		userEventsVO.setPaymentStatus("pending");
		
		this.userEventsService.insertUserEvent(userEventsVO);
		
		return new ModelAndView("redirect:/user/selectEvents");
	}
}
