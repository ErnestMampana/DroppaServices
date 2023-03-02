package com.droppa.webapi.DroppaServices.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DriverDTO {
	@NotNull
	@NotBlank
	@NotEmpty
	public String id;

	public String name;

	public String surname;

	public long celphone;

	public String companyId;
	
	public String vehicleId;

}
