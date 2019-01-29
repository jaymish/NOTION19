package com.notion.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;

import com.notion.service.*;

@Controller
public class UserProfileController {
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	RegService regService;
	
	@Autowired
	InstituteService instituteService;
	
	HttpSession session;
	
	@RequestMapping(value="/user/profile",method=RequestMethod.GET)
	public ModelAndView loadUserProfile(HttpServletRequest request,@ModelAttribute LoginVO loginVO,RegVO regVO1,UserProfileVO userProfileVO)
	{
		session=request.getSession();
		regVO1=(RegVO)session.getAttribute("regDetails");
		userProfileVO.setRegVO(regVO1);
		List<InstituteVO> instituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("/user/userProfile","userProfileData",userProfileVO).addObject("instituteLs", instituteList);
	}
	
	@RequestMapping(value="/user/insertUserProfile",method=RequestMethod.POST)
	public ModelAndView insertUserProfile(HttpServletRequest request,@ModelAttribute("userProfileData")@Valid UserProfileVO userProfileVO1,BindingResult result,RegVO regVO2)
	{
		session=request.getSession();
		regVO2=(RegVO)session.getAttribute("regDetails");
		
		this.userProfileService.insertUserProfile(userProfileVO1);
		
		regVO2.setProfileStatus("complete");
		this.regService.insertToRegister(regVO2);
		
		return new ModelAndView("/user/userDashboard");
	}
}
