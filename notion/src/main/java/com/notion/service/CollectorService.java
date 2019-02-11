package com.notion.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notion.model.CollectorVO;
import com.notion.dao.CollectorDAO;

@Service
public class CollectorService {
	
	@Autowired
	CollectorDAO collectorDAO;
	
	@Transactional
	public void insertCollection(CollectorVO collectorVO){
		this.collectorDAO.insertCollection(collectorVO);
	}
}
