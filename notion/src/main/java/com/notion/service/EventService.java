package com.notion.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.EventDAO;
import com.notion.model.EventVO;
import com.notion.model.UserEventsVO;

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
	
	@Transactional
	public List<EventVO> editEvent(EventVO eventVO1){
		List<EventVO> editEventLs=eventDAO.editEvent(eventVO1);
		return editEventLs;
	}
	
	@Transactional
	public void changeEventStatus(EventVO eventVO3){
		eventDAO.changeEventStatus(eventVO3);
	}
	
	@Transactional
	public void deleteEvent(EventVO eventVO4){
		eventDAO.deleteEvent(eventVO4);
	}
	
	@Transactional
	public List<EventVO> unselectedEvents(UserEventsVO userEventsVO){
		List<EventVO> unselectedEventsLs=this.eventDAO.unselectedEvents(userEventsVO);
		return unselectedEventsLs;
	}
}
