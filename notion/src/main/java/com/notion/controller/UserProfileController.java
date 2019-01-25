package com.notion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.UserProfileVO;
import com.notion.service.UserProfileService;

@Controller
public class UserProfileController {
	@Autowired
	UserProfileService userProfileService;
	
	@RequestMapping(value="/user/insertUserProfile",method=RequestMethod.POST)
	public ModelAndView insertUserProfile(@ModelAttribute("userProfileData") UserProfileVO userProfileVO)
	{
		this.userProfileService.insertUserProfile(userProfileVO);
		return new ModelAndView("/user/userDashboard");
	}
}
