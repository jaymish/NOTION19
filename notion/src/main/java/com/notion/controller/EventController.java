package com.notion.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;
import com.notion.service.*;

@Controller
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@RequestMapping(value="/admin/addEvent",method=RequestMethod.GET)
	public ModelAndView loadAddEvent()
	{
		return new ModelAndView("admin/addEvent","addEventData",new EventVO());
	}
	
	@RequestMapping(value="/admin/insertEvent",method=RequestMethod.POST)
	public ModelAndView insertEvent(EventVO eventVO)
	{
		eventVO.setStatus("active");
		this.eventService.insertEvent(eventVO);
		return new ModelAndView("redirect:/admin/viewEvents");
	}
	
	@RequestMapping(value="/admin/viewEvents",method=RequestMethod.GET)
	public ModelAndView loadviewEvents()
	{
		List<EventVO> viewEventsList=this.eventService.viewEvents();
		List<EventVO> techEventsList=new ArrayList<EventVO>();
		List<EventVO> nontechEventsList=new ArrayList<EventVO>();
		List<EventVO> inactiveEventsList=new ArrayList<EventVO>();
		for(EventVO eventLS : viewEventsList)
		{
			if(eventLS.getStatus().equals("active"))
			{
				if(eventLS.getEventType().equals("Technical"))
				{
					techEventsList.add(eventLS);
				}
				else if(eventLS.getEventType().equals("Non-Technical"))
				{
					nontechEventsList.add(eventLS);
				}
			}
			else if(eventLS.getStatus().equals("inactive"))
			{
				inactiveEventsList.add(eventLS);
			}
		}
		return new ModelAndView("admin/viewEvents","techeventslist",techEventsList).addObject("nontecheventslist", nontechEventsList).addObject("inactiveeventslist",inactiveEventsList);
	}
	
	@RequestMapping(value="/admin/editEvent",method=RequestMethod.GET)
	public ModelAndView loadeditEvent(@RequestParam("editEventId") int editEventId,EventVO eventVO1)
	{
		eventVO1.setEventId(editEventId);
		List<EventVO> editEventList=this.eventService.editEvent(eventVO1);
		return new ModelAndView("admin/editEvent","editEventData",editEventList.get(0));
	}
	
	@RequestMapping(value="/admin/updateEvent",method=RequestMethod.POST)
	public ModelAndView updateEvent(@ModelAttribute("editEventData") EventVO eventVO2)
	{
		this.eventService.insertEvent(eventVO2);
		return new ModelAndView("redirect:/admin/viewEvents");
	}
	
	@RequestMapping(value="/admin/changeEventStatus",method=RequestMethod.GET)
	public ModelAndView changeEventStatus(@RequestParam("changeEventStatusId") int changeEventStatusId,@RequestParam("eventStatus") String eventStatus,EventVO eventVO3)
	{
		eventVO3.setEventId(changeEventStatusId);
		if(eventStatus.equals("active"))
		{
			eventVO3.setStatus("inactive");
		}
		else if(eventStatus.equals("inactive"))
		{
			eventVO3.setStatus("active");
		}
		this.eventService.changeEventStatus(eventVO3);
		return new ModelAndView("redirect:/admin/viewEvents");
	}
	
	@RequestMapping(value="/admin/deleteEvent",method=RequestMethod.GET)
	public ModelAndView deleteEvent(@RequestParam("deleteEventId") int deleteEventId,EventVO eventVO4)
	{
		eventVO4.setEventId(deleteEventId);
		this.eventService.deleteEvent(eventVO4);
		return new ModelAndView("redirect:/admin/viewEvents");
	}
}
