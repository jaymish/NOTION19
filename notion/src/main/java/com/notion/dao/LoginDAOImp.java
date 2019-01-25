package com.notion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.*;

@Repository
public class LoginDAOImp implements LoginDAO{
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertToLogin(LoginVO loginVO)
	{
		try
			{
				 Session session=sessionFactory.openSession();
				 
				 Transaction transaction=session.beginTransaction();
				 
				 session.save(loginVO);
				 
				 transaction.commit();
				 
				 session.close();
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}
	public LoginVO getUser(LoginVO loginVO)
	{
		LoginVO loginVOObj=new LoginVO();
		try
		{
			Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from LoginVO where username='"+loginVO.getUsername()+"'");
			 
			 loginVOObj=(LoginVO)q.uniqueResult();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return loginVOObj;
	}
}
