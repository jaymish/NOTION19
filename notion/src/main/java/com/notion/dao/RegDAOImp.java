package com.notion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.notion.model.RegVO;

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
	
	public List<RegVO> getRegDetails(RegVO regVO1)
	{
		List<RegVO> regList=new ArrayList<RegVO>();
		try
		{
			Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from RegVO where loginVO.username='"+regVO1.getLoginVO().getUsername()+"'");
			 
			 regList=q.list();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return regList;
	}
	
	public List<RegVO> unverifiedUsers()
	{
		List<RegVO> unverifiedUsersList=new ArrayList<RegVO>();
		try
		{
			Session session=sessionFactory.openSession();
			 
			 Transaction transaction=session.beginTransaction();
			 
			 Query q=session.createQuery("from RegVO where loginVO.username in(select username from LoginVO where enabled='0')");
			 
			 unverifiedUsersList=q.list();
			 
			 transaction.commit();
			 
			 session.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return unverifiedUsersList;
	}
}
