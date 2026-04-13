package mg.healthpoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.SaveStaffRequest;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.exception.ForbiddenException;
import mg.healthpoint.exception.NotFoundException;
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
	
	@PostMapping("/api/staff/save")
	public ResponseEntity<Integer> saveStaff(Authentication auth, @Valid @RequestBody SaveStaffRequest form) throws NotFoundException, ForbiddenException {
		
		Staff staff = this.staffService.save(auth, form);
		return ResponseEntity.status(HttpStatus.CREATED).body(staff.getId());
		
	}

}