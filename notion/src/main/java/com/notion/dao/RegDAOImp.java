package com.notion.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.*;

@Repository
public class RegDAOImp implements RegDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertToRegister(RegVO regVO)
		{
			try
				{
					 Session session=sessionFactory.openSession();
					 
					 Transaction transaction=session.beginTransaction();
					 
					 session.saveOrUpdate(regVO);
					 
					 transaction.commit();
					 
					 session.close();
				}
			catch(Exception ex)
				{
					ex.printStackTrace();
				} 
		}
}
