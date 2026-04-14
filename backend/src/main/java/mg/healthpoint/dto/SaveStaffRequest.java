package mg.healthpoint.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SaveStaffRequest(
	Integer id,
	
	@NotNull(message = "Account is missing")
	SaveAccountRequest account,
	
	@NotEmpty(message = "Admin is missing")
	Boolean admin
) {}