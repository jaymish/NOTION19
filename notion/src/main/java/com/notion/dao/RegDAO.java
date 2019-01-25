package com.notion.dao;

import com.notion.model.RegVO;

public interface RegDAO {
	
	public void insertToRegister(RegVO regVO);
	public RegVO getRegDetails(RegVO regVO1);
}
