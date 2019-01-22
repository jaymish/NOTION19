package com.notion.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.model.*;
import com.notion.dao.*;

import java.util.List;

@Service
public class InstituteService {
	
	@Autowired
	InstituteDAO instituteDAO;
	
	@Transactional
	public void insertInstitute(InstituteVO instituteVO){
		instituteDAO.insertInstitute(instituteVO);
	}
	
	@Transactional
	public List<InstituteVO> viewInstitutes(){
		List<InstituteVO> viewInstituteLs=this.instituteDAO.viewInstitutes();
		return viewInstituteLs;
	}
	
	@Transactional
	public List<InstituteVO> editInstitute(InstituteVO instituteVO1){
		List<InstituteVO> editInstituteLs=this.instituteDAO.editInstitute(instituteVO1);
		return editInstituteLs;
	}
	
	@Transactional
	public void updateInstitute(InstituteVO instituteVO2){
		instituteDAO.updateInstitute(instituteVO2);
	}
	
	@Transactional
	public void deleteInstitute(InstituteVO instituteVO3){
		instituteDAO.deleteInstitute(instituteVO3);
	}
}
