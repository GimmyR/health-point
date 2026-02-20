package mg.healthpoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import mg.healthpoint.service.StaffService;

@RestController
@AllArgsConstructor
public class StaffRestController {
	
	private StaffService staffService;
	
	@GetMapping("/api/is-staff")
	public ResponseEntity<Boolean> isStaff(Authentication auth) {
		
		boolean result = staffService.isStaff(auth.getName());
		return ResponseEntity.ok(result);
		
	}

}