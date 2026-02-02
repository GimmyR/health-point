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
import mg.healthpoint.repository.ParameterRepository;

@ExtendWith(MockitoExtension.class)
public class ParameterServiceUnitTest {
	
	@Mock
	private ParameterRepository parameterRepository;
	
	@InjectMocks
	private ParameterService parameterService;
	
	@Test
	public void test_findUniqueById() throws NotFoundException {
		
		Account account = new Account(1, "johndoe", "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28");
		Patient patient = new Patient(1, account, "Room 67", "Mental illness", new ArrayList<Parameter>());
		Parameter parameter = new Parameter(1, patient, "Temperature", "*C", new ArrayList<>());
		when(parameterRepository.findById(1)).thenReturn(Optional.of(parameter));
		
		Parameter param = parameterService.findUniqueById(1);
		assertNotNull(param);
		assertEquals(parameter.getName(), param.getName());
		
	}

}
