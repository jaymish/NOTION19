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
public class InstituteDAOImp implements InstituteDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertInstitute(InstituteVO instituteVO)
	{
		try
			{
				 Session session=sessionFactory.openSession();
				 
				 Transaction transaction=session.beginTransaction();
				 
				 session.saveOrUpdate(instituteVO);
				 
				 transaction.commit();
				 
				 session.close();
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}
	
	public List<InstituteVO> viewInstitutes()
	{
		List<InstituteVO> ls=new ArrayList<InstituteVO>();
		try
		{	
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from InstituteVO");
			
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
	public List<InstituteVO> editInstitute(InstituteVO instituteVO1)
	{
		List<InstituteVO> ls=new ArrayList<InstituteVO>();
		try
		{	
			Session session=sessionFactory.openSession();
			
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from InstituteVO where instituteId='"+instituteVO1.getInstituteId()+"'");
			
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
	public void updateInstitute(InstituteVO instituteVO2)
	{
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.update(instituteVO2);
			 
			 transaction.commit();
			 
			 session.close();
		}
	catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void deleteInstitute(InstituteVO instituteVO3)
	{
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.delete(instituteVO3);
			 
			 transaction.commit();
			 
			 session.close();
		}
	catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
