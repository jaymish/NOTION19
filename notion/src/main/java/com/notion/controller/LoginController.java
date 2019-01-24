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

import com.notion.model.LoginVO;
import com.notion.model.RegVO;
import com.notion.service.LoginService;
import com.notion.service.RegService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
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
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView loadLogout()
	{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="checkUser",method=RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username") String checkuser,LoginVO loginVO)
	{
		loginVO.setUsername(checkuser);
		List<LoginVO> checkUserLs=new ArrayList<LoginVO>();
		checkUserLs=this.loginService.checkUser(loginVO);
		Boolean reply;
		if(checkUserLs.isEmpty())
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
	public ModelAndView insertRegData(@ModelAttribute RegVO regVO,LoginVO loginVO)
	{
		loginVO.setUsername(regVO.getLoginVO().getUsername());
		loginVO.setPassword(regVO.getLoginVO().getPassword());
		loginVO.setEnabled(1);
		loginVO.setRole("ROLE_USER");
		this.loginService.insertToLogin(loginVO);
		
		regVO.setLoginVO(loginVO);
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
	public ModelAndView loadUserDashboard() 
	{		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		return new ModelAndView("user/userDashboard");
	}
}
