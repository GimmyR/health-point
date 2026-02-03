package mg.healthpoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherException(Exception e) {
		
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}

}
