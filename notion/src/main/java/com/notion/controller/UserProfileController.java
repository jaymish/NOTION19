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
	
	@RequestMapping(value="/user/profile",method=RequestMethod.GET)
	public ModelAndView loadUserProfile(@ModelAttribute LoginVO loginVO4,RegVO regVO1,UserProfileVO userProfileVO)
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		
		loginVO4.setUsername(userName);
		List<LoginVO> loginDetailsList=new ArrayList<LoginVO>();
		loginDetailsList=this.loginService.getUser(loginVO4);
		loginVO4=loginDetailsList.get(0);
		regVO1.setLoginVO(loginVO4);
		List<RegVO> regDetailsList=new ArrayList<RegVO>();
		regDetailsList=this.regService.getRegDetails(regVO1);
		regVO1=regDetailsList.get(0);
		List<InstituteVO> instituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("/user/userProfile","userProfileData",userProfileVO).addObject("instituteLs", instituteList);
	}
	
	@RequestMapping(value="/user/insertUserProfile",method=RequestMethod.POST)
	public ModelAndView insertUserProfile(@ModelAttribute("userProfileData") UserProfileVO userProfileVO1)
	{
		this.userProfileService.insertUserProfile(userProfileVO1);
		return new ModelAndView("/user/userDashboard");
	}
}
