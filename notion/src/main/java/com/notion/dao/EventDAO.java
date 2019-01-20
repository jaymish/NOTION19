package com.notion.dao;

import java.util.List;

import com.notion.model.EventVO;

public interface EventDAO {
	public void insertEvent(EventVO eventVO);
	public List<EventVO> viewEvents();
}
