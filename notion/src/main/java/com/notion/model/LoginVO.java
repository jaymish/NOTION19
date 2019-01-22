package com.notion.model;

import javax.persistence.*;

@Entity

@Table(name="login")

public class LoginVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int loginId;
	
	@Column
	private String username;		
	
	@Column
	private String password;
	
	@Column
	private int enable;
	
	@Column
	private String role;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
