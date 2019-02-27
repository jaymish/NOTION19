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
	public List<UserProfileVO> getUserProfileByReg(UserProfileVO userProfileVO1){
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileDAO.getUserProfileByReg(userProfileVO1);
		return profileData;
	}
	
	@Transactional
	public List<UserProfileVO> getUserProfileById(UserProfileVO userProfileVO2){
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		profileData=this.userProfileDAO.getUserProfileById(userProfileVO2);
		return profileData;
	}
	
	@Transactional
	public List<UserProfileVO> noEventsSelected(){
		List<UserProfileVO> eventsNotSelectedList=new ArrayList<UserProfileVO>();
		eventsNotSelectedList=this.userProfileDAO.noEventsSelected();
		return eventsNotSelectedList;
	}
	
	@Transactional
	public List<UserProfileVO> pendingPayers(){
		List<UserProfileVO> pendingPayersList=new ArrayList<UserProfileVO>();
		pendingPayersList=this.userProfileDAO.pendingPayers();
		return pendingPayersList;
	}
	
	@Transactional
	public List<UserProfileVO> getUserProfileByQR(UserProfileVO userProfileVO3){
		List<UserProfileVO> userByQR = new ArrayList<UserProfileVO>();
		userByQR=this.userProfileDAO.getUserProfileByQR(userProfileVO3);
		return userByQR;
	}
}
