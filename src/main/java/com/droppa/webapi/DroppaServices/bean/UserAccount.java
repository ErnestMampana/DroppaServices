package com.droppa.webapi.DroppaServices.bean;

public class UserAccount {

	public Person owner;
	public boolean confirmed;
	public int otp;
	public String status;
	
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserAccount(Person owner, boolean confirmed, int otp, String status) {
		super();
		this.owner = owner;
		this.confirmed = confirmed;
		this.otp = otp;
		this.status = status;
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
