package com.notion.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.RegDAO;
import com.notion.model.RegVO;

@Service
public class RegService {
	@Autowired
	RegDAO regDAO;
	
	@Transactional
	public void insertToRegister(RegVO regVO){
		regDAO.insertToRegister(regVO);
	}
	
	@Transactional
	public  List<RegVO> getRegDetails(RegVO regVO1){
		List<RegVO> getDetails=new ArrayList<RegVO>();
		getDetails=this.regDAO.getRegDetails(regVO1);
		return getDetails;
	}
}
