package com.notion.model;

import javax.persistence.*;
import com.notion.model.*;

@Entity

@Table(name="UserEvents")
public class UserEventsVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int userEventId;
	
	@ManyToOne
	UserProfileVO userProfileVO;
	
	@ManyToOne
	EventVO eventVO1;
	
	@Column
	private String paymentStatus;
	
	@Column
	private String attendance;
	
	@Column
	private String teamMember1;
	
	@Column
	private String teamMember2;
	
	@Column
	private String teamMember3;
	
	@Column
	private String teamMember4;
	
	@Column
	private String teamMember5;

	public int getUserEventId() {
		return userEventId;
	}

	public void setUserEventId(int userEventId) {
		this.userEventId = userEventId;
	}

	public UserProfileVO getUserProfileVO() {
		return userProfileVO;
	}

	public void setUserProfileVO(UserProfileVO userProfileVO) {
		this.userProfileVO = userProfileVO;
	}

	public EventVO getEventVO1() {
		return eventVO1;
	}

	public void setEventVO1(EventVO eventVO1) {
		this.eventVO1 = eventVO1;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getAttendance() {
		return attendance;
	}

	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}

	public String getTeamMember1() {
		return teamMember1;
	}

	public void setTeamMember1(String teamMember1) {
		this.teamMember1 = teamMember1;
	}

	public String getTeamMember2() {
		return teamMember2;
	}

	public void setTeamMember2(String teamMember2) {
		this.teamMember2 = teamMember2;
	}

	public String getTeamMember3() {
		return teamMember3;
	}

	public void setTeamMember3(String teamMember3) {
		this.teamMember3 = teamMember3;
	}

	public String getTeamMember4() {
		return teamMember4;
	}

	public void setTeamMember4(String teamMember4) {
		this.teamMember4 = teamMember4;
	}

	public String getTeamMember5() {
		return teamMember5;
	}

	public void setTeamMember5(String teamMember5) {
		this.teamMember5 = teamMember5;
	}
}
