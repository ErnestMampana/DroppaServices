package com.droppa.webapi.Droppa.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class VehicleDTO {
	@NotBlank
	@NotEmpty
	@NotNull
	public String registration;

	public String make;

	public String type;

	public String discExpiryDate;

	public String companyId;

}
