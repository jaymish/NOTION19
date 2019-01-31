package com.notion.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.*;
import com.notion.model.*;

@Service
public class UserProfileService {
	@Autowired
	UserProfileDAO userProfileDAO;
	
	@Transactional
	public void insertUserProfile(UserProfileVO userProfileVO){
		this.userProfileDAO.insertUserProfile(userProfileVO);
	}
	
	@Transactional
	public List<UserProfileVO> getUserProfile(){
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileDAO.getUserProfile();
		return profileData;
	}
	
	@Transactional
	public List<UserProfileVO> getUserProfile(UserProfileVO userProfileVO1){
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileDAO.getUserProfile(userProfileVO1);
		return profileData;
	}
}
