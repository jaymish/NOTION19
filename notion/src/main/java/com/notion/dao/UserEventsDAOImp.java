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
	
	public void insertUserEvent(UserEventsVO userEventsVO){
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
}
