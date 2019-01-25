package com.notion.service;

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
}
