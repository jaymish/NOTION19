package com.notion.model;

import javax.persistence.*;
import com.notion.model.*;
@Entity

@Table(name="registration")

public class RegVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int registrationId;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String profileStatus;
	
	@ManyToOne
	LoginVO loginVO;

	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}

	public LoginVO getLoginVO() {
		return loginVO;
	}

	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
}
