package com.droppa.webapi.DroppaServices.bean;

public class UserAccount {

	public Person owner;
	public boolean confirmed;
	public int otp;
	
	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserAccount(Person owner, boolean confirmed) {
		super();
		this.owner = owner;
		this.confirmed = confirmed;
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
