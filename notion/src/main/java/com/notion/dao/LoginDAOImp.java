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
	public List<LoginVO> getUser(LoginVO loginVO1)
	{
		List<LoginVO> loginList=new ArrayList<LoginVO>();
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			 
			Query q=session.createQuery("from LoginVO where username='"+loginVO1.getUsername()+"'");
			
			loginList=q.list();
			 
			transaction.commit();
			 
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return loginList;
	}
	
	public void changeEnabled(LoginVO loginVO2)
	{
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			 
			session.createQuery("update LoginVO set enabled='"+loginVO2.getEnabled()+"' where username='"+loginVO2.getUsername()+"'").executeUpdate();
			 
			transaction.commit();
			 
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void resetPassword(LoginVO loginVO3)
	{
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			 
			session.createQuery("update LoginVO set password='"+loginVO3.getPassword()+"' where username='"+loginVO3.getUsername()+"'").executeUpdate();
			 
			transaction.commit();
			 
			session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
