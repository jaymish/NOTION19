package com.notion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.LoginVO;
import com.notion.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loadLogin()
	{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView redirectLogin()
	{
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="checkUser",method=RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username") String checkuser,LoginVO loginVO)
	{
		loginVO.setUsername(checkuser);
		List<LoginVO> checkUserLs=new ArrayList<LoginVO>();
		checkUserLs=this.loginService.checkUser(loginVO);
		String reply;
		if(!(checkUserLs.isEmpty()))
		{
			reply="Username already exist";
		}
		else
		{
			reply="Username not found";
		}
		return new ModelAndView("checkUsername","response",reply);
	}
}
