package com.notion.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.dao.CollectorDAO;
import com.notion.model.CollectorVO;

@Service
public class CollectorService {
	
	@Autowired
	CollectorDAO collectorDAO;
	
	@Transactional
	public void insertCollection(CollectorVO collectorVO){
		this.collectorDAO.insertCollection(collectorVO);
	}
	
	@Transactional
	public List<CollectorVO> viewCollection(){
		List<CollectorVO> collectorList=new ArrayList<CollectorVO>();
		collectorList=this.collectorDAO.viewCollection();
		return collectorList;
	}
}
