package mg.healthpoint.dto;

public record PatientItemResponse(
	Integer id,
	String firstname,
	String lastname,
	String gender,
	String room
) {}