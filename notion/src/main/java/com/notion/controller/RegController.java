package com.notion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;
import com.notion.service.*;

@Controller
public class RegController {
	@Autowired
	RegService regService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="signup",method=RequestMethod.GET)
	public ModelAndView loadSignup()
	{
		return new ModelAndView("signup","regData",new RegVO());
	}
}
