package com.droppa.webapi.DroppaServices.pojo;

public class DropDetails {
	private String name;
	private String surname;
	private String contact;

	public DropDetails() {
		super();
	}

	public DropDetails(String name, String surname, String contact) {
		super();
		this.name = name;
		this.surname = surname;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
