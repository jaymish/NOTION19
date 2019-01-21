package com.notion.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.EventVO;

@Repository
public class EventDAOImp implements EventDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertEvent(EventVO eventVO){
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.saveOrUpdate(eventVO);
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public List<EventVO> viewEvents(){
		List<EventVO> ls=new ArrayList<EventVO>();
		try
		{	
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from EventVO");
			
			ls=q.list();
			
			transaction.commit();
			
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ls;
	}
	public List<EventVO> editEvent(EventVO eventVO1){
		List<EventVO> ls=new ArrayList<EventVO>();
		try
		{
			
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from EventVO where id='"+eventVO1.getEventId()+"'");
			
			ls=q.list();
			
			transaction.commit();
			
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return ls;
	}
	public void updateEvent(EventVO eventVO2)
	{
		try
		{	
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			session.update(eventVO2);
			
			transaction.commit();
			
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void changeEventStatus(EventVO eventVO3)
	{
		try
		{
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			session.createQuery("update EventVO set status='"+eventVO3.getStatus()+"' where eventId='"+eventVO3.getEventId()+"'").executeUpdate();
			
			transaction.commit();
			
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void deleteEvent(EventVO eventVO4)
	{
		try
		{	
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			session.delete(eventVO4);
			
			transaction.commit();
			
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
