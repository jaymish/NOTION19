package com.notion.model;

import javax.persistence.*;
import com.notion.model.*;

@Entity

@Table(name="userProfile")

public class UserProfileVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int profileId;
	
	@ManyToOne
	RegVO regVO;
	
	@Column
	private String enrollment;
	
	@Column
	private String semester;
	
	@ManyToOne
	InstituteVO instituteVO;
	
	@Column
	private String contact;
	
	@Column
	private String uniqueQR;

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	
	public RegVO getRegVO() {
		return regVO;
	}

	public void setRegVO(RegVO regVO) {
		this.regVO = regVO;
	}

	public String getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(String enrollment) {
		this.enrollment = enrollment;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public InstituteVO getInstituteVO() {
		return instituteVO;
	}

	public void setInstituteVO(InstituteVO instituteVO) {
		this.instituteVO = instituteVO;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getUniqueQR() {
		return uniqueQR;
	}

	public void setUniqueQR(String uniqueQR) {
		this.uniqueQR = uniqueQR;
	}
}
