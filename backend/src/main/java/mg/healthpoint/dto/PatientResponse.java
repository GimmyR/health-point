package mg.healthpoint.dto;

import java.time.LocalDateTime;

public record PatientResponse(
	Integer id,
	String room,
	String diagnosis,
	AccountResponse account,
	Iterable<ParameterResponse> parameters,
	Iterable<LocalDateTime> entryDates
) {}
