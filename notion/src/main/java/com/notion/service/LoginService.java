package com.notion.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.*;
import com.notion.model.*;

@Service
public class LoginService {
	@Autowired
	LoginDAO loginDAO;
	
	@Transactional
	public void insertToLogin(LoginVO loginVO){
		this.loginDAO.insertToLogin(loginVO);
	}
	
	@Transactional
	public LoginVO getUser(LoginVO loginVO){
		LoginVO getUser=this.loginDAO.getUser(loginVO);
		return getUser;
	}
}
