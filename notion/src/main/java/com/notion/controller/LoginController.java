package com.notion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.LoginVO;
import com.notion.model.RegVO;
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
	
	HttpSession session;
	
	@RequestMapping(value="/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView redirectLogin()
	{
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loadLogin()
	{
		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView loadLogout(ModelMap model,HttpServletResponse response,HttpServletRequest request)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            request.getSession().invalidate();
            request.getSession().setAttribute("tempStatus", "success");
            request.getSession().setAttribute("statusText", "Logout Successfully!");
        }
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/user/logout",method=RequestMethod.GET)
	public String userLogout()
	{
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/admin/logout",method=RequestMethod.GET)
	public String adminLogout()
	{
		return "redirect:/logout";
	}
	
	@RequestMapping(value="checkUser",method=RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("username") String checkuser,LoginVO loginVO)
	{
		loginVO.setUsername(checkuser);
		List<LoginVO> emailCheck=new ArrayList<LoginVO>();
		emailCheck=this.loginService.getUser(loginVO);
		Boolean reply;
		if(emailCheck.isEmpty())
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
	public ModelAndView loadAdminDashboard(HttpServletRequest request)
	{
		session=request.getSession();
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		session.setAttribute("adminMail", userName);
		return new ModelAndView("admin/adminDashboard");
	}
	
	@RequestMapping(value="/user/Dashboard",method=RequestMethod.GET)
	public ModelAndView loadUserDashboard(HttpServletRequest request,@ModelAttribute LoginVO loginVO3,RegVO regVO1) 
	{	
		session=request.getSession();
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		session.setAttribute("userMail", userName);
		
		loginVO3.setUsername(userName);
		regVO1.setLoginVO(loginVO3);
		List<RegVO> regDetailsList=new ArrayList<RegVO>();
		regDetailsList=this.regService.getRegDetails(regVO1);
		regVO1=regDetailsList.get(0);
		session.setAttribute("regDetails", regVO1);
		
		if(regVO1.getProfileStatus().equals("complete"))
		{
			return new ModelAndView("/user/userDashboard");
		}
		else
		{
			return new ModelAndView("redirect:/user/profile");
		}
	}
}
