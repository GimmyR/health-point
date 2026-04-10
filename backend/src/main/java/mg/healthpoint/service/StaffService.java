package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.repository.AccountRepository;
import mg.healthpoint.repository.StaffRepository;


@Service
@AllArgsConstructor
@Transactional
public class StaffService {
	
	private StaffRepository staffRepository;
	private AccountRepository accountRepository;
	
	public List<Staff> findAll() {
		
		return staffRepository.findAll();
		
	}
	
	public Staff findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Staff> opt = staffRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Staff not found");
		
	}
	
	public boolean isStaff(String username) {
		
		Optional<Staff> opt = staffRepository.findByAccountUsername(username);
		return opt.isPresent();
		
	}
	
	public Staff save(Staff staff) {
		
		return staffRepository.save(staff);
		
	}
	
	public void delete(Staff staff) {
		
		staffRepository.delete(staff);
		
	}
	
	public boolean adminExists() {
		
		Optional<Staff> opt = this.staffRepository.findByProfession("Admin");
		
		return opt.isPresent();
		
	}
	
	public void saveAdmin(String username, String password) {
		
		Account account = new Account();
		account.editUsername(username);
		account.editPassword(password);
		account = accountRepository.save(account);
		
		Staff staff = new Staff();
		staff.editAccount(account);
		staff.editProfession("Admin");
		staffRepository.save(staff);
		
	}

}
