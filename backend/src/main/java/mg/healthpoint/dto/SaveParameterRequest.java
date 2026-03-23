package mg.healthpoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaveParameterRequest(
		Integer id,
		
		@NotNull
		@Positive
		Integer patientId,
		
		@NotBlank
		String name,
		
		String unit,
		Double min,
		Double max
) {}