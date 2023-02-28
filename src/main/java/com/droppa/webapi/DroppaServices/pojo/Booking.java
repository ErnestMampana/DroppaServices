package com.droppa.webapi.DroppaServices.pojo;

import com.droppa.webapi.DroppaServices.core.BookingStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Booking {

	@NotNull
	@NotEmpty
	@NotBlank
	private String bookingId;
	private Adress pickupAdress;
	private Adress dropOffAdress;
	private String userId;
	private DropDetails pickupDetails;
	private DropDetails dropOffDetails;
	private String bookingDate;
	private double price;
	private String assinedDriver;
	private BookingStatus status;

	public Booking() {
		super();
	}

	public Booking(@NotNull @NotEmpty @NotBlank String bookingId, Adress pickupAdress, Adress dropOffAdress,
			String userId, DropDetails pickupDetails, DropDetails dropOffDetails, String bookingDate, double price,
			String assinedDriver, BookingStatus status) {
		super();
		this.bookingId = bookingId;
		this.pickupAdress = pickupAdress;
		this.dropOffAdress = dropOffAdress;
		this.userId = userId;
		this.pickupDetails = pickupDetails;
		this.dropOffDetails = dropOffDetails;
		this.bookingDate = bookingDate;
		this.price = price;
		this.assinedDriver = assinedDriver;
		this.status = status;
	}

	public String getAssinedDriver() {
		return assinedDriver;
	}

	public void setAssinedDriver(String assinedDriver) {
		this.assinedDriver = assinedDriver;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Adress getPickupAdress() {
		return pickupAdress;
	}

	public void setPickupAdress(Adress pickupAdress) {
		this.pickupAdress = pickupAdress;
	}

	public Adress getDropOffAdress() {
		return dropOffAdress;
	}

	public void setDropOffAdress(Adress dropOffAdress) {
		this.dropOffAdress = dropOffAdress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DropDetails getPickupDetails() {
		return pickupDetails;
	}

	public void setPickupDetails(DropDetails pickupDetails) {
		this.pickupDetails = pickupDetails;
	}

	public DropDetails getDropOffDetails() {
		return dropOffDetails;
	}

	public void setDropOffDetails(DropDetails dropOffDetails) {
		this.dropOffDetails = dropOffDetails;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
