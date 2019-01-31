package com.notion.dao;

import java.util.List;

import com.notion.model.UserProfileVO;

public interface UserProfileDAO {
	public void insertUserProfile(UserProfileVO userProfileVO);
	public List<UserProfileVO> getUserProfile();
	public List<UserProfileVO> getUserProfile(UserProfileVO userProfileVO1);
}
