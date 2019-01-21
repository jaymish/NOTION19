package com.notion.dao;

import java.util.List;

import com.notion.model.InstituteVO;

public interface InstituteDAO {
	public void insertInstitute(InstituteVO instituteVO);
	public List<InstituteVO> viewInstitutes();
}
