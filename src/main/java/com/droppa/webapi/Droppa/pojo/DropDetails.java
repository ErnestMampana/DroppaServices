package com.droppa.webapi.Droppa.pojo;

public class DropDetails {
	private String name;
	private String surname;
	private long contact;

	public DropDetails() {
		super();
	}

	public DropDetails(String name, String surname, long contact) {
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

}
