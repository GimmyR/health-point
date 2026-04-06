package mg.healthpoint.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.ParameterResponse;
import mg.healthpoint.dto.SaveParameterRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.service.ParameterService;

@RestController
@AllArgsConstructor
public class ParameterRestController {
	
	private ParameterService parameterService;
	
	@GetMapping("/api/parameters/{id}")
	public ResponseEntity<ParameterResponse> getParameter(@PathVariable Integer id) throws NotFoundException {
		
		Parameter parameter = this.parameterService.findUniqueById(id);
		ParameterResponse result = this.parameterService.mapToParameterResponseWithoutEntries(parameter);
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping("/api/parameter/save")
	public ResponseEntity<Integer> saveParameter(@Valid @RequestBody SaveParameterRequest form) throws BadRequestException, NotFoundException {
		
		Parameter parameter = this.parameterService.save(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(parameter.getId());
		
	}
	
	@PostMapping("/api/parameter/remove/{id}")
	public ResponseEntity<Integer> removeParameter(@PathVariable Integer id) throws NotFoundException {
		
		Parameter parameter = this.parameterService.findUniqueWithEntriesById(id);
		this.parameterService.delete(parameter);
		return ResponseEntity.status(HttpStatus.CREATED).body(parameter.getPatient().getId());
		
	}

}