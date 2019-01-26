package com.notion.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.notion.service.InstituteService;
import com.notion.service.LoginService;
import com.notion.service.RegService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
	@Autowired
	InstituteService instituteService;
	
	@RequestMapping(value="/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView redirectLogin()
	{
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loadLogin()
	{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public ModelAndView loadLogout()
	{
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="checkUser",method=RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username") String checkuser,LoginVO loginVO)
	{
		loginVO.setUsername(checkuser);
		loginVO=this.loginService.getUser(loginVO);
		Boolean reply;
		if(loginVO==null)
		{
			reply=true;
		}
		else
		{
			reply=false;
		}
		return new ModelAndView("checkUsername","response",reply);
	}
	
	@RequestMapping(value="insertRegData",method=RequestMethod.POST)
	public ModelAndView insertRegData(@ModelAttribute RegVO regVO,LoginVO loginVO1)
	{
		loginVO1.setUsername(regVO.getLoginVO().getUsername());
		loginVO1.setPassword(regVO.getLoginVO().getPassword());
		loginVO1.setEnabled(1);
		loginVO1.setRole("ROLE_USER");
		this.loginService.insertToLogin(loginVO1);
		
		regVO.setLoginVO(loginVO1);
		regVO.setProfileStatus("empty");
		this.regService.insertToRegister(regVO);
		
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/admin/Dashboard",method=RequestMethod.GET)
	public ModelAndView loadAdminDashboard()
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return new ModelAndView("admin/adminDashboard");
	}
	
	@RequestMapping(value="/user/Dashboard",method=RequestMethod.GET)
	public ModelAndView loadUserDashboard(@ModelAttribute LoginVO loginVO4,RegVO regVO1,UserProfileVO userProfileVO) 
	{		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		
		loginVO4.setUsername(userName);
		loginVO4=this.loginService.getUser(loginVO4);
		regVO1.setLoginVO(loginVO4);
		regVO1=this.regService.getRegDetails(regVO1);
		if(regVO1.getProfileStatus().equals("complete"))
		{
			return new ModelAndView("user/userDashboard","userData",regVO1);
		}
		else
		{
			return new ModelAndView("redirect:/user/profile");
		}
	}
}
