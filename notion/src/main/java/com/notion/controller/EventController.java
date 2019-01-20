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
	
	@RequestMapping(value="/addEvent",method=RequestMethod.GET)
	public ModelAndView loadAddEvent()
	{
		return new ModelAndView("addEvent","addEventData",new EventVO());
	}
	
	@RequestMapping(value="/insertEvent",method=RequestMethod.POST)
	public ModelAndView insertEvent(EventVO eventVO)
	{
		eventVO.setStatus("active");
		this.eventService.insertEvent(eventVO);
		return new ModelAndView("redirect:viewEvents");
	}
	
	@RequestMapping(value="/viewEvents",method=RequestMethod.GET)
	public ModelAndView loadviewEvents()
	{
		List<EventVO> viewEventsList=this.eventService.viewEvents();
		return new ModelAndView("viewEvents","eventsList",viewEventsList);
	}
}
