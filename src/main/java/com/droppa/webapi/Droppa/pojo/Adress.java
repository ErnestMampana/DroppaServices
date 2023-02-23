package com.droppa.webapi.Droppa.pojo;

public class Adress {
	private int standNumber;
	private String streetName;
	private String suburb;
	private String province;
	private int postalCode;

	public Adress() {
		super();
	}

	public Adress(int standNumber, String streetName, String suburb, String province, int postalCode) {
		super();
		this.standNumber = standNumber;
		this.streetName = streetName;
		this.suburb = suburb;
		this.province = province;
		this.postalCode = postalCode;
	}

	public int getStandNumber() {
		return standNumber;
	}

	public void setStandNumber(int standNumber) {
		this.standNumber = standNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

}
