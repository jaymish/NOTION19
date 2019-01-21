package com.notion.model;

import javax.persistence.*;

@Entity

@Table(name="Events")
public class EventVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int eventId;
	
	@Column
	private String eventName;
	
	@Column
	private String eventDescription;
	
	@Column
	private String eventType;
	
	@Column
	private String eventBranch;
	
	@Column
	private String participationType;
	
	@Column
	private int teamMin;
	
	@Column
	private int teamMax;
	
	@Column
	private int eventPrice;
	
	@Column
	private String status;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventBranch() {
		return eventBranch;
	}

	public void setEventBranch(String eventBranch) {
		this.eventBranch = eventBranch;
	}

	public String getParticipationType() {
		return participationType;
	}

	public void setParticipationType(String participationType) {
		this.participationType = participationType;
	}

	public int getTeamMin() {
		return teamMin;
	}

	public void setTeamMin(int teamMin) {
		this.teamMin = teamMin;
	}

	public int getTeamMax() {
		return teamMax;
	}

	public void setTeamMax(int teamMax) {
		this.teamMax = teamMax;
	}

	public int getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(int eventPrice) {
		this.eventPrice = eventPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
