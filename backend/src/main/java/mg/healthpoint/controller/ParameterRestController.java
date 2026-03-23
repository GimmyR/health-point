package mg.healthpoint.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.SaveParameterRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.service.ParameterService;

@RestController
@AllArgsConstructor
public class ParameterRestController {
	
	private ParameterService parameterService;
	
	@PostMapping("/api/parameter/save")
	public ResponseEntity<Integer> saveParameter(@Valid @RequestBody SaveParameterRequest form) throws BadRequestException, NotFoundException {
		
		Parameter parameter = this.parameterService.save(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(parameter.getId());
		
	}

}