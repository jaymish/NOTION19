package com.notion.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.*;
import com.notion.model.*;

@Service
public class RegService {
	@Autowired
	RegDAO regDAO;
	
	@Transactional
	public void insertToRegister(RegVO regVO){
		regDAO.insertToRegister(regVO);
	}
	
	@Transactional
	public RegVO getRegDetails(RegVO regVO1){
		RegVO getDetails=this.regDAO.getRegDetails(regVO1);
		return getDetails;
	}
}
