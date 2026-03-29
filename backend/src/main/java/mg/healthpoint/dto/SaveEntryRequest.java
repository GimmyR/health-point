package mg.healthpoint.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaveEntryRequest(
		Integer id,
		
		@NotNull(message = "Parameter ID is missing")
		@Positive(message = "Parameter ID should be a positive integer")
		Integer parameterId,
		
		@NotNull(message = "Entry date & time are missing")
		LocalDateTime entryDate,
		
		@NotNull(message = "Entry value is missing")
		Double value
) {}