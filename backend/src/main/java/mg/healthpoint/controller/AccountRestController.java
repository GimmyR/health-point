package mg.healthpoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.SignInRequest;
import mg.healthpoint.service.AccountService;

@RestController
@AllArgsConstructor
public class AccountRestController {
	
	private AccountService accountService;
	
	@PostMapping("/api/sign-in")
	public ResponseEntity<String> signIn(@Valid @RequestBody SignInRequest form) {
		
		accountService.authenticate(form);
		String token = accountService.generateJWT(form.username());
		return ResponseEntity.ok(token);
		
	}

}
