package mg.healthpoint.service;

import java.time.Instant;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.SaveAccountRequest;
import mg.healthpoint.dto.SignInRequest;
import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Role;
import mg.healthpoint.repository.AccountRepository;

@Service
@AllArgsConstructor
@Transactional
public class AccountService {
	
	private JwtEncoder jwtEncoder;
	private AuthenticationManager authenticationManager;
	private AccountRepository accountRepository;
	
	public void authenticate(SignInRequest form) {
		
		Authentication auth = new UsernamePasswordAuthenticationToken(form.username(), form.password());
		authenticationManager.authenticate(auth);
		
	}
	
	public String generateJWT(String username) {
		
		Account account = accountRepository.findWithRolesByUsername(username);
		
		JwtClaimsSet payload = JwtClaimsSet.builder()
				.issuedAt(Instant.now())
				.subject(account.getUsername())
				.claim("roles", account.getRoles().stream().map(role -> role.getName()).toArray(String[]::new))
				.build();
		
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
				JwsHeader.with(MacAlgorithm.HS512).build(), 
				payload
		);
		
		return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
		
	}
	
	public void remove(Account account) {
		
		this.accountRepository.delete(account);
		
	}
	
	public Account save(SaveAccountRequest form, List<Role> roles) throws BadRequestException {
		
		if(form.password() == null || form.password().isBlank())
			throw new BadRequestException("Password is missing");
		
		Account account = new Account();
		account.editUsername(form.username());
		account.editPassword(form.password());
		account.editFirstname(form.firstname());
		account.editLastname(form.lastname());
		account.editGender(form.gender());
		account.editDateOfBirth(form.dateOfBirth());
		account.editAddress(form.address());
		account.editContact(form.contact());
		
		if(roles != null)
			account.editRoles(roles);
		
		return this.accountRepository.save(account);
		
	}
	
	public List<Account> findAllWithRolesWithoutAdmin() {
		
		return this.accountRepository.findAllWithRolesWithoutAdmin();
		
	}

}
