package mg.healthpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SavePatientRequest(
		Integer id,
		
		@NotBlank(message = "Room is missing")
		String room,
		
		@NotBlank(message = "Diagnosis is missing")
		String diagnosis,
		
		@NotNull(message = "Account is missing")
		SaveAccountRequest account
) {}
