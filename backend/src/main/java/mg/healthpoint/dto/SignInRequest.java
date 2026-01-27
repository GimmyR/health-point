package mg.healthpoint.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
		@NotBlank(message = "Invalid credentials")
		String username, 
		
		@NotBlank(message = "Invalid credentials")
		String password
) {}