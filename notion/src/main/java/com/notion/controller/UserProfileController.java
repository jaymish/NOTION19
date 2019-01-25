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
import com.notion.service.*;

@Controller
public class UserProfileController {
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	InstituteService instituteService;
	
	@RequestMapping(value="userProfile",method=RequestMethod.GET)
	public ModelAndView loadUserProfile(@ModelAttribute("regUser") RegVO regVO1,UserProfileVO userProfileVO)
	{
		userProfileVO.setRegVO(regVO1);
		List<InstituteVO> instituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("userProfile","userProfileData",userProfileVO).addObject("instituteLs", instituteList);
	}
}
