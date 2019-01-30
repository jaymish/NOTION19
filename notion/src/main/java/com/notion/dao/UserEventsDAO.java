package com.notion.dao;

import java.util.List;

import com.notion.model.UserEventsVO;

public interface UserEventsDAO {
	public void insertUserEvent(UserEventsVO userEventsVO);
	public List<UserEventsVO> viewUserEvents(UserEventsVO userEventsVO1);
	public List<UserEventsVO> viewUserEvents();
	public void removeUserEvent(UserEventsVO userEventsVO3);
}
