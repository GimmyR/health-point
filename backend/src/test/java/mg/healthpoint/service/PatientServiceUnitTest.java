package mg.healthpoint.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mg.healthpoint.dto.PatientResponse;
import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.ParameterEntry;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.entity.Role;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.repository.ParameterRepository;
import mg.healthpoint.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceUnitTest {
	
	@Mock
	private PatientRepository patientRepository;
	
	@Mock
	private ParameterRepository parameterRepository;
	
	@InjectMocks
	private PatientService patientService;
	
	@Test
	public void test_findUniqueById() throws NotFoundException {
		
		Role role = new Role(1, "Patient");
		Account account = new Account(1, "johndoe", "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28", Arrays.asList(role));
		Patient patient = new Patient(1, account, "Room 67", "Mental illness", new ArrayList<Parameter>());
		Optional<Patient> opt = Optional.of(patient);
		when(patientRepository.findById(1)).thenReturn(opt);
		
		Patient pat = patientService.findUniqueById(1);
		assertNotNull(pat);
		assertEquals(patient.getRoom(), pat.getRoom());
		
	}
	
	@Test
	public void test_findUniqueByUsername() throws NotFoundException {
		
		String username = "johndoe";
		Role role = new Role(1, "Patient");
		Account account = new Account(1, username, "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28", Arrays.asList(role));
		Patient patient = new Patient(1, account, "208", "Heartache", new ArrayList<>());
		Parameter parameter = new Parameter(1, patient, "Temperature", "*C", 35.0, 42.0, new ArrayList<>());
		Parameter parameter2 = new Parameter(1, patient, "Temperature", "*C", 35.0, 42.0, new ArrayList<>());
		List<Parameter> parameters = Arrays.asList(parameter);
		patient.editParameters(parameters);
		List<ParameterEntry> entries = Arrays.asList(new ParameterEntry(1, parameter, LocalDateTime.now(), 36.5));
		parameter2.editDetails(entries);
		
		when(patientRepository.findWithParametersByUsername(username)).thenReturn(Optional.of(patient));
		when(parameterRepository.findWithEntriesById(parameters.getFirst().getId())).thenReturn(Optional.of(parameter2));
		
		Patient pat = patientService.findUniqueByUsername(username);
		assertEquals(entries.getFirst().getValue(), pat.getParameters().getFirst().getDetails().getFirst().getValue());
		
	}
	
	@Test
	public void test_mapToPatientResponse() {
		
		String username = "johndoe";
		Role role = new Role(1, "Patient");
		Account account = new Account(1, username, "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28", Arrays.asList(role));
		Patient patient = new Patient(1, account, "208", "Heartache", new ArrayList<>());
		Parameter parameter = new Parameter(1, patient, "Temperature", "*C", 35.0, 42.0, new ArrayList<>());
		ParameterEntry entry = new ParameterEntry(1, parameter, LocalDateTime.now(), 36.5);
		
		List<ParameterEntry> entries = Arrays.asList(entry);
		parameter.editDetails(entries);
		
		List<Parameter> parameters = Arrays.asList(parameter);
		patient.editParameters(parameters);
		
		String dateTime = "2026-02-11T09:00";
		List<LocalDateTime> entryDates = Arrays.asList(LocalDateTime.parse(dateTime));
		
		PatientResponse response = patientService.mapToPatientResponse(patient, entryDates);
		assertEquals(response.account().firstname(), patient.getAccount().getFirstname());
		assertEquals(response.details().room(), patient.getRoom());
		assertEquals(response.parameters().iterator().next().name(), parameter.getName());
		assertEquals(response.parameters().iterator().next().entries().iterator().next().value(), entry.getValue());
		assertEquals(response.entryDates().iterator().next().toString(), dateTime);
		
	}

}
