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
	
	@RequestMapping(value="/admin/addInstitute")
	public ModelAndView loadaddInstitute()
	{
		return new ModelAndView("admin/addInstitute","addInstituteData",new InstituteVO());
	}
	
	@RequestMapping(value="/admin/insertInstitute",method=RequestMethod.POST)
	public String insertInstitute(@ModelAttribute InstituteVO instituteVO)
	{
		this.instituteService.insertInstitute(instituteVO);
		return "redirect:/admin/addInstitute";
	}
	
	@RequestMapping(value="/admin/viewInstitutes",method=RequestMethod.GET)
	public ModelAndView loadviewInstitutes()
	{
		List<InstituteVO> viewInstituteList=this.instituteService.viewInstitutes();
		return new ModelAndView("admin/viewInstitutes","viewinstitutelist",viewInstituteList);
	}
	
	@RequestMapping(value="/admin/editInstitute",method=RequestMethod.GET)
	public ModelAndView loadeditInstitute(@RequestParam("editInstituteId") int editInstituteId,InstituteVO instituteVO1)
	{
		instituteVO1.setInstituteId(editInstituteId);
		List<InstituteVO> editInstituteList=this.instituteService.editInstitute(instituteVO1);
		return new ModelAndView("admin/editInstitute","editInstituteData",editInstituteList.get(0));
	}
	
	@RequestMapping(value="/admin/updateInstitute",method=RequestMethod.POST)
	public ModelAndView updateInstitute(@ModelAttribute("editInstituteData") InstituteVO instituteVO2)
	{
		this.instituteService.updateInstitute(instituteVO2);
		return new ModelAndView("redirect:/admin/viewInstitutes");
	}
	
	@RequestMapping(value="/admin/deleteInstitute",method=RequestMethod.GET)
	public ModelAndView deleteInstitute(@RequestParam("deleteInstituteId") int deleteInstituteId,InstituteVO instituteVO3)
	{
		instituteVO3.setInstituteId(deleteInstituteId);
		this.instituteService.deleteInstitute(instituteVO3);
		return new ModelAndView("redirect:/admin/viewInstitutes");
	}
}
