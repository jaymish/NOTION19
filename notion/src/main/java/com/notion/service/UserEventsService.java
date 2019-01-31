package com.notion.service;
import java.util.ArrayList;
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
	
	@Transactional
	public List<UserEventsVO> viewUserEvents(UserEventsVO userEventsVO1){
		List<UserEventsVO> userEventsList=new ArrayList<UserEventsVO>();
		userEventsList=this.userEventsDAO.viewUserEvents(userEventsVO1);
		return userEventsList;
	}
	
	@Transactional
	public List<UserEventsVO> paymentComplete(){
		List<UserEventsVO> userEventsList=new ArrayList<UserEventsVO>();
		userEventsList=this.userEventsDAO.paymentComplete();
		return userEventsList;
	}
	
	@Transactional
	public void removeUserEvent(UserEventsVO userEventsVO3){
		this.userEventsDAO.removeUserEvent(userEventsVO3);
	}
	
	@Transactional
	public List<UserEventsVO> paymentPending(){
		List<UserEventsVO> paymentPendingList=new ArrayList<UserEventsVO>();
		paymentPendingList=this.userEventsDAO.paymentPending();
		return paymentPendingList;
	}
	
	@Transactional
	public void completePayment(UserEventsVO userEventsVO4){
		this.userEventsDAO.completePayment(userEventsVO4);
	}
}
