package mg.healthpoint.dto;

public record FindAllStaffsResponse(
	Integer id,
	String username,
	String firstname,
	String lastname,
	String gender
) {}