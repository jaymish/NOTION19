package com.notion.dao;

import java.util.List;

import com.notion.model.LoginVO;

public interface LoginDAO {
	public void insertToLogin(LoginVO loginVO);
	public List<LoginVO> getUser(LoginVO loginV1);
	public void changeEnabled(LoginVO loginVO2);
	public void resetPassword(LoginVO loginVO3);
}
