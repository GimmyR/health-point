package mg.healthpoint.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveAccountRequest(
		@NotBlank(message = "Username is missing")
		String username,
		
		@NotBlank(message = "Password is missing")
		String password,
		
		@NotBlank(message = "Firstname is missing")
		String firstname,
		
		@NotBlank(message = "Lastname is missing")
		String lastname,
		
		@NotBlank(message = "Gender is missing")
		String gender,
		
		@NotNull(message = "Date of birth is missing")
		LocalDate dateOfBirth,
		
		@NotBlank(message = "Address is missing")
		String address,
		
		@NotBlank(message = "Contact is missing")
		String contact
) {}