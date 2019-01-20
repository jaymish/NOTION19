package com.notion.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.EventDAO;
import com.notion.model.EventVO;

@Service
public class EventService {

	@Autowired
	EventDAO eventDAO;
	
	@Transactional
	public void insertEvent(EventVO eventVO){
		eventDAO.insertEvent(eventVO);
	}
	
	@Transactional
	public List<EventVO> viewEvents(){
		List<EventVO> viewEventLs=eventDAO.viewEvents();
		return viewEventLs;
	}
}
