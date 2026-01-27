package mg.healthpoint.service;

import java.time.Instant;
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

import mg.healthpoint.dto.SignInRequest;

@Service
@AllArgsConstructor
@Transactional
public class AccountService {
	
	private JwtEncoder jwtEncoder;
	private AuthenticationManager authenticationManager;
	
	public void authenticate(SignInRequest form) {
		
		Authentication auth = new UsernamePasswordAuthenticationToken(form.username(), form.password());
		authenticationManager.authenticate(auth);
		
	}
	
	public String generateJWT(String username) {
		
		JwtClaimsSet payload = JwtClaimsSet.builder()
				.issuedAt(Instant.now())
				.subject(username)
				.build();
		
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
				JwsHeader.with(MacAlgorithm.HS512).build(), 
				payload
		);
		
		return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
		
	}

}
