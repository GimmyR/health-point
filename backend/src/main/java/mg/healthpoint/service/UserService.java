package mg.healthpoint.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import mg.healthpoint.entity.Account;
import mg.healthpoint.repository.AccountRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Account> opt = accountRepository.findWithRolesByUsername(username);
		
		if(opt.isEmpty())
			throw new UsernameNotFoundException(String.format("%s not found", username));
		
		Account account = opt.get();
		
		UserDetails user = User.withUsername(account.getUsername())
								.password(account.getPassword())
								.roles(account.getRoles().stream().map(role -> role.getName()).toArray(String[]::new))
								.build();
		
		return user;
		
	}

}