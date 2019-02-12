package com.notion.dao;

import java.util.List;

import com.notion.model.CollectorVO;

public interface CollectorDAO {
	public void insertCollection(CollectorVO collectorVO);
	public List<CollectorVO> viewCollection();
}
