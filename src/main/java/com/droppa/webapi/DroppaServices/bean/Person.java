package com.droppa.webapi.DroppaServices.bean;

public class Person {
	private String id;
	private String userName;
	private String surname;
	private String celphone;
	private String walletBalance;
	private String email;

	public Person() {
		super();
	}

	

	public Person(String id, String userName, String surname, String celphone, String walletBalance, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.surname = surname;
		this.celphone = celphone;
		this.walletBalance = walletBalance;
		this.email = email;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



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

	@Override
	public String toString() {
		return "Person [id=" + id + ", userName=" + userName + ", surname=" + surname + ", celphone=" + celphone
				+ ", walletBalance=" + walletBalance + "]";
	}

	

}
