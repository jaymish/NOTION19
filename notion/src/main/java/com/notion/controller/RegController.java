package com.notion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.RegVO;
import com.notion.service.EmailService;
import com.notion.service.LoginService;
import com.notion.service.RegService;

@Controller
public class RegController {
	@Autowired
	RegService regService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="signup",method=RequestMethod.GET)
	public ModelAndView loadSignup(@RequestParam(value="mail",required=false)String mailmsg)
	{
		return new ModelAndView("signup","regData",new RegVO()).addObject("mailmsg",mailmsg);
	}
	
	@RequestMapping(value="/admin/unverified",method=RequestMethod.GET)
	public ModelAndView unverifiedUsers()
	{
		List<RegVO> unverifiedUsersLs=this.regService.unverifiedUsers();
		return new ModelAndView("/admin/unverifiedUsers","unverifiedUsersLs",unverifiedUsersLs);
	}
	
	@RequestMapping(value="/admin/userVerification",method=RequestMethod.GET)
	public String sendToUnverifiedUsers(HttpServletResponse response,@RequestParam("username") String email,@RequestParam("firstname") String name) throws IOException
	{
		PrintWriter out=response.getWriter();
		String msg=this.emailService.sendVerificationLink(email, name);
		if(msg=="sent")
		{
			out.print("sent");
		}
		else
		{
			out.print("notsent");
		}
		
		return "redirect:/admin/unverified";
	}
}
