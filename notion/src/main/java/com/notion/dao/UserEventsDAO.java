package com.notion.dao;

import java.util.List;

import com.notion.model.UserEventsVO;
import com.notion.model.UserProfileVO;

public interface UserEventsDAO {
	public void insertUserEvent(UserEventsVO userEventsVO);
	public List<UserEventsVO> viewUserEvents(UserEventsVO userEventsVO1);
	public void removeUserEvent(UserEventsVO userEventsVO3);
	public List<UserEventsVO> paymentComplete();
	public List<UserEventsVO> paymentPending();
	public void collectPayment(UserProfileVO userProfileVO);
}
