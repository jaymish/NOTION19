package com.notion.dao;

import java.util.List;

import com.notion.model.EventVO;
import com.notion.model.UserEventsVO;

public interface EventDAO {
	public void insertEvent(EventVO eventVO);
	public List<EventVO> viewEvents();
	public List<EventVO> editEvent(EventVO eventVO1);
	public void changeEventStatus(EventVO eventVO3);
	public void deleteEvent(EventVO eventVO4);
	public List<EventVO> unselectedEvents(UserEventsVO userEventsVO);
}
