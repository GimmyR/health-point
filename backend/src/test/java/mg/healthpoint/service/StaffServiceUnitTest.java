package mg.healthpoint.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mg.healthpoint.entity.Account;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.repository.StaffRepository;

@ExtendWith(MockitoExtension.class)
public class StaffServiceUnitTest {
	
	@Mock
	private StaffRepository staffRepository;
	
	@InjectMocks
	private StaffService staffService;
	
	@Test
	public void test_findUniqueById() throws NotFoundException {
		
		Account account = new Account(1, "johndoe", "pwdJohn", "John", "Doe", "Male", LocalDate.now(), "Itaosy", "033 72 209 28");
		Staff staff = new Staff(1, account, "Doctor");
		Optional<Staff> opt = Optional.of(staff);
		when(staffRepository.findById(1)).thenReturn(opt);
		
		Staff st = staffService.findUniqueById(1);
		assertNotNull(st);
		assertEquals(staff.getProfession(), st.getProfession());
		
	}

}