package mg.healthpoint.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mg.healthpoint.dto.PatientItemResponse;
import mg.healthpoint.dto.PatientResponse;
import mg.healthpoint.dto.SavePatientRequest;
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
	
	@GetMapping("/api/patients")
	public ResponseEntity<List<PatientItemResponse>> getPatients() {
		
		List<Patient> patients = patientService.findAllWithAccount();
		List<PatientItemResponse> results = patientService.mapToListOfPatientItemResponse(patients);
		return ResponseEntity.ok(results);
		
	}
	
	@GetMapping("/api/patients/{id}")
	public ResponseEntity<PatientResponse> getPatient(@PathVariable Integer id) throws NotFoundException {
		
		Patient patient = patientService.findUniqueWithParametersById(id);
		List<LocalDateTime> entryDates = parameterEntryService.findDistinctEntryDatesByPatientId(patient.getId());
		PatientResponse response = patientService.mapToPatientResponse(patient, entryDates);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/api/patient-details/{id}")
	public ResponseEntity<PatientResponse> getPatientDetails(@PathVariable Integer id) throws NotFoundException {
		
		Patient patient = patientService.findUniqueById(id);
		PatientResponse response = patientService.mapToPatientResponseWithoutParametersAndEntryDates(patient);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/api/is-patient")
	public ResponseEntity<Boolean> isPatient(Authentication auth) {
		
		boolean result = patientService.isPatient(auth.getName());
		return ResponseEntity.ok(result);
		
	}
	
	@PostMapping("/api/save-patient")
	public ResponseEntity<Integer> savePatient(@Valid @RequestBody SavePatientRequest form) throws NotFoundException, BadRequestException {
		
		Patient patient = patientService.save(form);
		return ResponseEntity.status(HttpStatus.CREATED).body(patient.getId());
		
	}
	
	@PostMapping("/api/patient/remove/{id}")
	public ResponseEntity<Boolean> removePatient(@PathVariable Integer id) throws NotFoundException {
		
		Patient patient = patientService.findUniqueById(id);
		patientService.delete(patient);
		return ResponseEntity.status(HttpStatus.CREATED).body(true);
		
	}

}