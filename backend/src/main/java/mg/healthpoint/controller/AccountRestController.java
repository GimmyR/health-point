package mg.healthpoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.EditPasswordRequest;
import mg.healthpoint.dto.SignInRequest;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.exception.ForbiddenException;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.service.AccountService;
import mg.healthpoint.service.StaffService;

@RestController
@AllArgsConstructor
public class AccountRestController {
	
	private AccountService accountService;
	private StaffService staffService;
	
	@PostMapping("/api/sign-in")
	public ResponseEntity<String> signIn(@Valid @RequestBody SignInRequest form) throws NotFoundException {
		
		accountService.authenticate(form);
		String token = accountService.generateJWT(form.username());
		return ResponseEntity.ok(token);
		
	}
	
	@GetMapping("/api/signed-in")
	public ResponseEntity<String> signedIn(Authentication auth) {
		
		return ResponseEntity.ok(auth.getName());
		
	}
	
	@PatchMapping("/api/accounts/edit-password/{accountId}")
	public Boolean editPassword(Authentication auth, @PathVariable Integer accountId, @Valid @RequestBody EditPasswordRequest form) throws NotFoundException, ForbiddenException {
		
		Staff staff = this.staffService.findUniqueByAccountUsername(auth.getName());
		
		if(!staff.getAdmin())
			throw new ForbiddenException("You are not allowed to do this");
		
		return this.accountService.editPassword(accountId, form);
		
	}

}
