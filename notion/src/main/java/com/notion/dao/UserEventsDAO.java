package com.notion.dao;

import java.util.List;

import com.notion.model.UserEventsVO;

public interface UserEventsDAO {
	public void insertUserEvent(UserEventsVO userEventsVO);
	public List<UserEventsVO> viewUserEvents(UserEventsVO userEventsVO1);
	public void removeUserEvent(UserEventsVO userEventsVO3);
	public List<UserEventsVO> paymentComplete();
	public List<UserEventsVO> paymentPending();
	public void completePayment(UserEventsVO userEventsVO4);
}
