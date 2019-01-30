package com.notion.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.UserEventsVO;

@Repository
public class UserEventsDAOImp implements UserEventsDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertUserEvent(UserEventsVO userEventsVO)
	{
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.saveOrUpdate(userEventsVO);
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public List<UserEventsVO> viewUserEvents(UserEventsVO userEventsVO1)
	{
		List<UserEventsVO> userEventsList=new ArrayList<UserEventsVO>();
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from UserEventsVO where regVO1='"+userEventsVO1.getRegVO1().getRegistrationId()+"'");
			 
			 userEventsList=q.list();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return userEventsList;
	}
	
	public List<UserEventsVO> viewUserEvents()
	{
		List<UserEventsVO> userEventsList=new ArrayList<UserEventsVO>();
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from UserEventsVO");
			 
			 userEventsList=q.list();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return userEventsList;
	}
	
	public void removeUserEvent(UserEventsVO userEventsVO3)
	{
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.delete(userEventsVO3);
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
