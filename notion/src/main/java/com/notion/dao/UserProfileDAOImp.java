package com.notion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.UserProfileVO;

@Repository
public class UserProfileDAOImp implements UserProfileDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	public void insertUserProfile(UserProfileVO userProfileVO)
	{
		try
		{
			 Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 session.saveOrUpdate(userProfileVO);
			 
			 transaction.commit();
			 
			 session.close();
		}
	catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public List<UserProfileVO> getUserProfile()
	{
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from UserProfileVO");
			
			profileData=q.list();
			
			transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return profileData;
	}
	
	public List<UserProfileVO> getUserProfile(UserProfileVO userProfileVO1)
	{
		List<UserProfileVO> profileData=new ArrayList<UserProfileVO>();
		try
		{
			Session session=sessionFactory.openSession();
			 
			Transaction transaction=session.beginTransaction();
			
			Query q=session.createQuery("from UserProfileVO where regVO = '"+userProfileVO1.getRegVO().getRegistrationId()+"'");
			
			profileData=q.list();
			
			transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return profileData;
	}
}
