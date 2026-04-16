package mg.healthpoint.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.FindAllStaffsResponse;
import mg.healthpoint.dto.SaveAccountRequest;
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
	
	@GetMapping("/api/staffs")
	public ResponseEntity<List<FindAllStaffsResponse>> findAllStaffs(Authentication auth) throws NotFoundException, ForbiddenException {
		
		List<Staff> staffs = this.staffService.findAllWithAccountWithoutAdmin(auth);
		
		return ResponseEntity.ok(staffs.stream().map(staff -> new FindAllStaffsResponse(
			staff.getId(),
			staff.getAccount().getUsername(),
			staff.getAccount().getFirstname(), 
			staff.getAccount().getLastname(), 
			staff.getAccount().getGender()
		)).toList());
		
	}
	
	@GetMapping("/api/staffs/{id}")
	public ResponseEntity<SaveStaffRequest> findUniqueStaff(Authentication auth, @PathVariable Integer id) throws NotFoundException, ForbiddenException {
		
		Staff staff = this.staffService.findUniqueByIdWithAccount(auth, id);
		SaveStaffRequest result = new SaveStaffRequest(
			staff.getId(), 
			new SaveAccountRequest(
				staff.getAccount().getUsername(), 
				null, 
				staff.getAccount().getFirstname(), 
				staff.getAccount().getLastname(), 
				staff.getAccount().getGender(), 
				staff.getAccount().getDateOfBirth(), 
				staff.getAccount().getAddress(), 
				staff.getAccount().getContact()
			), 
			null
		);
		
		return ResponseEntity.ok(result);
		
	}
	
	@GetMapping("/api/is-admin")
	public ResponseEntity<Boolean> isAdmin(Authentication auth) throws NotFoundException {
		
		Staff staff = this.staffService.findUniqueByAccountUsername(auth.getName());
		return ResponseEntity.ok(staff.getAdmin());
		
	}
	
	@PostMapping("/api/staff/remove/{id}")
	public ResponseEntity<Boolean> removeStaff(Authentication auth, @PathVariable Integer id) throws NotFoundException, ForbiddenException {
		
		Staff staff = this.staffService.findUniqueById(id);
		this.staffService.delete(auth, staff);
		return ResponseEntity.status(HttpStatus.CREATED).body(true);
		
	}

}