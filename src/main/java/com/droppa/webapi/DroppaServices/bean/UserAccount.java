package com.droppa.webapi.DroppaServices.bean;

import jakarta.validation.constraints.NotNull;

public class UserAccount {

	@NotNull
	private String id;
	private Person owner;
	private boolean confirmed;
	private int otp;
	private String status;

	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccount(@NotNull String id, Person owner, boolean confirmed, int otp, String status) {
		super();
		this.id = id;
		this.owner = owner;
		this.confirmed = confirmed;
		this.otp = otp;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Person getOwner() {
		return owner;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

}
