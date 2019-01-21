package com.notion.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.notion.model.*;
import com.notion.service.*;

@Controller
public class InstituteController {
	@Autowired
	InstituteService instituteService;
	
	@RequestMapping(value="/addInstitute")
	public ModelAndView loadaddInstitute()
	{
		return new ModelAndView("addInstitute","addInstituteData",new InstituteVO());
	}
	
	@RequestMapping(value="/insertInstitute",method=RequestMethod.POST)
	public ModelAndView insertState(@ModelAttribute InstituteVO instituteVO)
	{
		this.instituteService.insertInstitute(instituteVO);
		return new ModelAndView("redirect:/viewInstitutes");
	}
	
	@RequestMapping(value="/viewInstitutes",method=RequestMethod.GET)
	public ModelAndView loadviewInstitutes()
	{
		List<InstituteVO> viewInstituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("viewInstitutes","viewinstitutelist",viewInstituteList);
	}
}
