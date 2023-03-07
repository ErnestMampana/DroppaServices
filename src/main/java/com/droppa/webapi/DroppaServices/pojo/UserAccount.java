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
	private String password;
	private String token;

	public UserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAccount(@NotNull String id, Person owner, boolean confirmed, int otp, AccountStatus status,
			String password, String token) {
		super();
		this.id = id;
		this.owner = owner;
		this.confirmed = confirmed;
		this.otp = otp;
		this.status = status;
		this.password = password;
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Person getOwner() {
		return owner;
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

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
