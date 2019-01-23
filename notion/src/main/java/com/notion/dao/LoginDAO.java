package com.notion.dao;

import java.util.List;

import com.notion.model.LoginVO;

public interface LoginDAO {
	public void insertToLogin(LoginVO loginVO);
	public List<LoginVO> checkUser(LoginVO loginVO);
}
