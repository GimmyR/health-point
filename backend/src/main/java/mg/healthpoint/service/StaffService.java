package mg.healthpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import mg.healthpoint.repository.AccountRepository;
import mg.healthpoint.repository.RoleRepository;
import mg.healthpoint.repository.StaffRepository;


@Service
@AllArgsConstructor
@Transactional
public class StaffService {
	
	private StaffRepository staffRepository;
	private AccountRepository accountRepository;
	private RoleRepository roleRepository;
	
	public List<Staff> findAll() {
		
		return staffRepository.findAll();
		
	}
	
	public Staff findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Staff> opt = staffRepository.findById(id);
		
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
	
	public Staff save(Authentication auth, SaveStaffRequest form) throws NotFoundException, ForbiddenException {
		
		Staff authenticated = this.findUniqueByAccountUsername(auth.getName());
		
		if(!authenticated.getProfession().equals("Admin"))
			throw new ForbiddenException("You are not allowed to do this");
		
		Staff staff = null;
		Optional<Role> opt = this.roleRepository.findById(2);
		List<Role> roles = new ArrayList<Role>();
		roles.add(opt.get());
		
		if(form.id() != null)
			staff = this.saveStaff(form, roles);
		
		else {
			
			Account account = this.saveAccount(form.account(), roles);
			staff = new Staff();
			staff.editAccount(account);
			staff.editProfession(form.profession());
			
		} return staffRepository.save(staff);
		
	}
	
	private Staff saveStaff(SaveStaffRequest form, List<Role> roles) throws NotFoundException {
		
		Staff staff = this.findUniqueById(form.id());
		staff.editProfession(form.profession());
		staff.getAccount().editFirstname(form.account().firstname());
		staff.getAccount().editLastname(form.account().lastname());
		staff.getAccount().editGender(form.account().gender());
		staff.getAccount().editDateOfBirth(form.account().dateOfBirth());
		staff.getAccount().editAddress(form.account().address());
		staff.getAccount().editContact(form.account().contact());
		staff.getAccount().editRoles(roles);
		
		return staff;
		
	}
	
	private Account saveAccount(SaveAccountRequest form, List<Role> roles) {
		
		Account account = new Account();
		account.editFirstname(form.firstname());
		account.editLastname(form.lastname());
		account.editGender(form.gender());
		account.editDateOfBirth(form.dateOfBirth());
		account.editAddress(form.address());
		account.editContact(form.contact());
		account.editRoles(roles);
		
		return this.accountRepository.save(account);
		
	}
	
	public void delete(Staff staff) {
		
		staffRepository.delete(staff);
		
	}
	
	public boolean adminExists() {
		
		Optional<Staff> opt = this.staffRepository.findByProfession("Admin");
		
		return opt.isPresent();
		
	}
	
	public void saveAdmin(String username, String password) {
		
		Optional<Role> opt = this.roleRepository.findById(2);
		List<Role> roles = new ArrayList<Role>();
		roles.add(opt.get());
		
		Account account = new Account();
		account.editUsername(username);
		account.editPassword(password);
		account.editRoles(roles);
		account = accountRepository.save(account);
		
		Staff staff = new Staff();
		staff.editAccount(account);
		staff.editProfession("Admin");
		staffRepository.save(staff);
		
	}

}
