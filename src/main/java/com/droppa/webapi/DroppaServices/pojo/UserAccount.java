package com.droppa.webapi.DroppaServices.pojo;

import com.droppa.webapi.DroppaServices.core.AccountStatus;

import jakarta.validation.constraints.NotNull;

public class UserAccount {

	@NotNull
	private String id;
	private Person owner;
	private boolean confirmed;
	private int otp;
	private AccountStatus status;

	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserAccount(@NotNull String id, Person owner, boolean confirmed, int otp, AccountStatus status) {
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

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
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
