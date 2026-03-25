package mg.healthpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaveParameterRequest(
		Integer id,
		
		@NotNull(message = "Patient ID is missing")
		@Positive(message = "Patient ID should be a positive integer")
		Integer patientId,
		
		@NotBlank(message = "Parameter name is missing")
		String name,
		
		String unit,
		Double min,
		Double max
) {}