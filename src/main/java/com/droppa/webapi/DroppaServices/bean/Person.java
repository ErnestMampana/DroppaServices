package com.droppa.webapi.DroppaServices.bean;

public class Person {
	private String id;
	private String userName;
	private String surname;
	private String celphone;
	private String walletBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCelphone() {
		return celphone;
	}

	public void setCelphone(String celphone) {
		this.celphone = celphone;
	}

	public String getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(String walletBalance) {
		this.walletBalance = walletBalance;
	}

}
