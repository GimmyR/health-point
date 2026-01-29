package mg.healthpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.repository.AccountRepository;
import mg.healthpoint.repository.PatientRepository;
import mg.healthpoint.repository.StaffRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	
	private AccountRepository accountRepository;
	private PatientRepository patientRepository;
	private StaffRepository staffRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByUsername(username);
		
		if(account == null)
			throw new UsernameNotFoundException(String.format("%s not found", username));
		
		Optional<Patient> patient = patientRepository.findByAccountId(account.getId());
		Optional<Staff> staff = staffRepository.findByAccountId(account.getId());
		List<String> roles = new ArrayList<String>();
		
		if(patient.isPresent())
			roles.add("Patient");
		
		if(staff.isPresent())
			roles.add("Staff");
		
		if(roles.size() == 0)
			throw new UsernameNotFoundException("You are not a Patient or a Staff");
		
		UserDetails user = User.withUsername(account.getUsername())
								.password(account.getPassword())
								.roles((String[]) roles.toArray())
								.build();
		
		return user;
		
	}

}
