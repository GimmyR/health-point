package mg.healthpoint.dto;

public record StaffResponse(
		Integer id,
		AccountResponse account,
		Boolean admin
) {}