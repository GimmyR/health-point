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
import mg.healthpoint.dto.ParameterEntryResponse;
import mg.healthpoint.dto.SaveEntryRequest;
import mg.healthpoint.entity.ParameterEntry;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.service.ParameterEntryService;

@RestController
@AllArgsConstructor
public class ParameterEntryRestController {
	
	private ParameterEntryService parameterEntryService;
	
	@GetMapping("/api/entries/{id}")
	public ResponseEntity<ParameterEntryResponse> getParameterEntry(@PathVariable Integer id) throws NotFoundException {
		
		ParameterEntry entry = this.parameterEntryService.findUniqueById(id);
		return ResponseEntity.ok(this.parameterEntryService.mapToParameterEntryResponse(entry));
		
	}
	
	@PostMapping("/api/entry/save")
	public ResponseEntity<Integer> saveParameterEntry(@Valid @RequestBody SaveEntryRequest form) throws BadRequestException, NotFoundException {
		
		ParameterEntry entry = this.parameterEntryService.save(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(entry.getId());
		
	}

}