package com.notion.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.UserEventsDAO;
import com.notion.model.UserEventsVO;

@Service
public class UserEventsService {
	@Autowired
	UserEventsDAO userEventsDAO;
	
	@Transactional
	public void insertUserEvent(UserEventsVO userEventsVO){
		this.userEventsDAO.insertUserEvent(userEventsVO);
	}
}
