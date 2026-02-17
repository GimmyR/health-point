package mg.healthpoint.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mg.healthpoint.dto.PatientResponse;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.service.ParameterEntryService;
import mg.healthpoint.service.PatientService;

@RestController
@AllArgsConstructor
public class PatientRestController {
	
	private PatientService patientService;
	private ParameterEntryService parameterEntryService;
	
	@GetMapping("/api/patient")
	public ResponseEntity<PatientResponse> getPatient(Authentication auth) throws NotFoundException {
		
		Patient patient = patientService.findUniqueByUsername(auth.getName());
		List<LocalDateTime> entryDates = parameterEntryService.findDistinctEntryDatesByPatientId(patient.getId());
		PatientResponse response = patientService.mapToPatientResponse(patient, entryDates);
		return ResponseEntity.ok(response);
		
	}

}