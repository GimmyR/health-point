package mg.healthpoint.dto;

import jakarta.validation.constraints.NotBlank;

public record EditPasswordRequest(
	@NotBlank(message = "Old password is missing")
	String oldPassword,
	
	@NotBlank(message = "New password is missing")
	String newPassword
) {}