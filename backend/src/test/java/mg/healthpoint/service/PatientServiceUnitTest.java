package mg.healthpoint.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceUnitTest {
	
	@Mock
	private PatientRepository patientRepository;
	
	@InjectMocks
	private PatientService patientService;
	
	@Test
	public void test_findUniqueById() throws NotFoundException {
		
		Account account = new Account(1, "johndoe", "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28");
		Patient patient = new Patient(1, account, "Room 67", "Mental illness", new ArrayList<Parameter>());
		Optional<Patient> opt = Optional.of(patient);
		when(patientRepository.findById(1)).thenReturn(opt);
		
		Patient pat = patientService.findUniqueById(1);
		assertNotNull(pat);
		assertEquals(patient.getRoom(), pat.getRoom());
		
	}

}
