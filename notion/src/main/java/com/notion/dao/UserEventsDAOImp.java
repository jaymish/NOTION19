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
import com.notion.model.UserProfileVO;

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
			 
			 Query q=session.createQuery("from UserEventsVO where userProfileVO='"+userEventsVO1.getUserProfileVO().getProfileId()+"'");
			 
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
	
	public List<UserEventsVO> paymentComplete()
	{
		List<UserEventsVO> userEventsList=new ArrayList<UserEventsVO>();
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from UserEventsVO where paymentStatus = 'complete'");
			 
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
	
	public List<UserEventsVO> paymentPending()
	{
		List<UserEventsVO> paymentPendingList=new ArrayList<UserEventsVO>();
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from UserEventsVO where paymentStatus = 'pending'");
			 
			 paymentPendingList=q.list();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return paymentPendingList;
	}
	public void collectPayment(UserProfileVO userProfileVO)
	{
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			
			session.createQuery("update UserEventsVO set paymentStatus = 'complete' where userProfileVO='"+userProfileVO.getProfileId()+"'").executeUpdate();
			
			transaction.commit();
			 
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
