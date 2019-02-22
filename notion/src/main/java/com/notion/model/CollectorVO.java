package com.notion.model;

import javax.persistence.*;

@Entity

@Table(name="PaymentCollector")
public class CollectorVO {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int collectionId;
	
	@Column
	private String collectorUsername;
	
	@Column
	private String nameOfUser;
	
	@Column
	private int totalAmount;
	
	@Column
	private String time;

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectorUsername() {
		return collectorUsername;
	}

	public void setCollectorUsername(String collectorUsername) {
		this.collectorUsername = collectorUsername;
	}

	public String getNameOfUser() {
		return nameOfUser;
	}

	public void setNameOfUser(String nameOfUser) {
		this.nameOfUser = nameOfUser;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
