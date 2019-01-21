package com.notion.model;

import javax.persistence.*;

@Entity

@Table(name="institutes")

public class InstituteVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int instituteId;
	
	@Column
	private String instituteName;
	
	@Column
	private String instituteDescription;

	public int getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getInstituteDescription() {
		return instituteDescription;
	}

	public void setInstituteDescription(String instituteDescription) {
		this.instituteDescription = instituteDescription;
	}
}
