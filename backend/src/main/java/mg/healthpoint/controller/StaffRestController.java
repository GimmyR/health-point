package mg.healthpoint.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.FindAllAccountsResponse;
import mg.healthpoint.dto.SaveStaffRequest;
import mg.healthpoint.entity.Account;
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
	
	@GetMapping("/api/account/all")
	public ResponseEntity<List<FindAllAccountsResponse>> findAllAccounts(Authentication auth) throws NotFoundException, ForbiddenException {
		
		List<Account> accounts = this.staffService.findAllAccountsWithRoles(auth);
		List<FindAllAccountsResponse> results = new ArrayList<FindAllAccountsResponse>();
		accounts.forEach(acc -> { 
			results.add(new FindAllAccountsResponse(
					acc.getId(), 
					acc.getFirstname(), 
					acc.getLastname(), 
					acc.getGender(), 
					acc.getRoles())
			);
		});
		
		return ResponseEntity.ok(results);
		
	}

}