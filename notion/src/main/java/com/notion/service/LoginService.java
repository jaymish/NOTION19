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
	public List<LoginVO> getUser(LoginVO loginVO){
		List<LoginVO> getUser=new ArrayList<LoginVO>();
		getUser=this.loginDAO.getUser(loginVO);
		return getUser;
	}
}
