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
			
			Query q=session.createQuery("from EventVO where status='active'");
			
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
}
