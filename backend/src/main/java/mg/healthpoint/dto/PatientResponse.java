package mg.healthpoint.dto;

import java.time.LocalDateTime;

public record PatientResponse(
	AccountResponse account,
	PatientDetailsResponse details,
	Iterable<ParameterResponse> parameters,
	Iterable<LocalDateTime> entryDates
) {}
