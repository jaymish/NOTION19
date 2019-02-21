package com.notion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.RegVO;
import com.notion.service.LoginService;
import com.notion.service.RegService;

@Controller
public class RegController {
	@Autowired
	RegService regService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="signup",method=RequestMethod.GET)
	public ModelAndView loadSignup(@RequestParam(value="mail",required=false)String mailmsg)
	{
		return new ModelAndView("signup","regData",new RegVO()).addObject("mailmsg",mailmsg);
	}
}
