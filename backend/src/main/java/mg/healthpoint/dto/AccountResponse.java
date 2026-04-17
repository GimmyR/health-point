package mg.healthpoint.dto;

import java.time.LocalDate;

public record AccountResponse(
	String username,
	String password,
	String firstname,
	String lastname,
	String gender,
	LocalDate dateOfBirth,
	String address,
	String contact
) {

}
