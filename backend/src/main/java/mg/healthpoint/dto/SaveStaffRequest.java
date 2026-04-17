package mg.healthpoint.dto;

import jakarta.validation.constraints.NotNull;

public record SaveStaffRequest(
	Integer id,
	
	@NotNull(message = "Account is missing")
	SaveAccountRequest account,

	Boolean admin
) {}