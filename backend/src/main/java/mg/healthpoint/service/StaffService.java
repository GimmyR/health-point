package mg.healthpoint.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import mg.healthpoint.exception.ForbiddenException;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.dto.SaveAccountRequest;
import mg.healthpoint.dto.SaveStaffRequest;
import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Role;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.repository.StaffRepository;

@Service
@AllArgsConstructor
@Transactional
public class StaffService {
	
	private StaffRepository staffRepository;
	private AccountService accountService;
	private RoleService roleService;
	
	public List<Staff> findAllWithAccountWithoutAdmin(Authentication auth) throws NotFoundException, ForbiddenException {
		
		this.checkAdmin(auth);
		return staffRepository.findAllWithAccountWithoutAdmin();
		
	}
	
	public Staff findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Staff> opt = staffRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Staff not found");
		
	}
	
	public Staff findUniqueByIdWithAccount(Authentication auth, Integer id) throws NotFoundException, ForbiddenException {
		
		this.checkAdmin(auth);
		Optional<Staff> opt = staffRepository.findByIdWithAccount(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Staff not found");
		
	}
	
	public Staff findUniqueByAccountUsername(String username) throws NotFoundException {
		
		Optional<Staff> opt = staffRepository.findByAccountUsername(username);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Staff not found");
		
	}
	
	public boolean isStaff(String username) {
		
		Optional<Staff> opt = staffRepository.findByAccountUsername(username);
		return opt.isPresent();
		
	}
	
	public Staff save(Authentication auth, SaveStaffRequest form) throws NotFoundException, ForbiddenException, BadRequestException {
		
		this.checkAdmin(auth);
		Staff staff = null;
		
		if(form.id() != null)
			staff = this.saveStaff(form);
		
		else {
			
			Role role = this.roleService.findUniqueByName("Staff");
			Account account = this.accountService.save(form.account(), Arrays.asList(role));
			staff = new Staff();
			staff.editAccount(account);
			staff.editAdmin(false);
			
		} return staffRepository.save(staff);
		
	}
	
	private void checkAdmin(Authentication auth) throws NotFoundException, ForbiddenException {
		
		Staff authenticated = this.findUniqueByAccountUsername(auth.getName());
		
		if(!authenticated.getAdmin())
			throw new ForbiddenException("You are not allowed to do this");
		
	}
	
	private Staff saveStaff(SaveStaffRequest form) throws NotFoundException {
		
		Staff staff = this.findUniqueById(form.id());
		
		if(form.admin() == null)
			staff.editAdmin(false);
		
		else staff.editAdmin(form.admin());
		
		staff.getAccount().editUsername(form.account().username());
		staff.getAccount().editFirstname(form.account().firstname());
		staff.getAccount().editLastname(form.account().lastname());
		staff.getAccount().editGender(form.account().gender());
		staff.getAccount().editDateOfBirth(form.account().dateOfBirth());
		staff.getAccount().editAddress(form.account().address());
		staff.getAccount().editContact(form.account().contact());
		
		return staff;
		
	}
	
	public void delete(Authentication auth, Staff staff) throws NotFoundException, ForbiddenException {
		
		this.checkAdmin(auth);
		staffRepository.delete(staff);
		accountService.remove(staff.getAccount());
		
	}
	
	public boolean adminExists() {
		
		Optional<Staff> opt = this.staffRepository.findByAdmin(true);
		
		return opt.isPresent();
		
	}
	
	public void saveAdmin(String username, String password) throws NotFoundException, BadRequestException {
		
		Role role = this.roleService.findUniqueByName("Staff");
		SaveAccountRequest form = new SaveAccountRequest(username, password, null, null, null, null, null, null);
		Account account = accountService.save(form, Arrays.asList(role));
		
		Staff staff = new Staff();
		staff.editAccount(account);
		staff.editAdmin(true);
		staffRepository.save(staff);
		
	}
	
	public List<Account> findAllAccountsWithRolesWithoutAdmin(Authentication auth) throws NotFoundException, ForbiddenException {
		
		this.checkAdmin(auth);
		return this.accountService.findAllWithRolesWithoutAdmin();
		
	}

}
