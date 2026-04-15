package mg.healthpoint.dto;

import java.util.List;

import mg.healthpoint.entity.Role;

public record FindAllAccountsResponse(
	Integer id,
	String firstname,
	String lastname,
	String gender,
	List<Role> roles
) {}