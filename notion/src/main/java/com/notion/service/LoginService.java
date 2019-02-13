package com.notion.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.LoginDAO;
import com.notion.model.LoginVO;

@Service
public class LoginService {
	@Autowired
	LoginDAO loginDAO;
	
	@Transactional
	public void insertToLogin(LoginVO loginVO){
		this.loginDAO.insertToLogin(loginVO);
	}
	
	@Transactional
	public List<LoginVO> getUser(LoginVO loginVO1){
		List<LoginVO> getUser=new ArrayList<LoginVO>();
		getUser=this.loginDAO.getUser(loginVO1);
		return getUser;
	}
	
	@Transactional
	public void changeEnabled(LoginVO loginVO2){
		this.loginDAO.changeEnabled(loginVO2);
	}
	
	@Transactional
	public void resetPassword(LoginVO loginVO3){
		this.loginDAO.resetPassword(loginVO3);
	}
}
