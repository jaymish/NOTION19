package com.notion.dao;

import java.util.List;

import com.notion.model.RegVO;

public interface RegDAO {
	
	public void insertToRegister(RegVO regVO);
	public List<RegVO> getRegDetails(RegVO regVO1);
}
