package mg.healthpoint.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PatientRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "johndoe")
	public void test_getPatient() throws Exception {
		
		mockMvc.perform(get("/api/patient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.account.firstname", is("John")))
				.andExpect(jsonPath("$.details.room", is("26C")))
				.andExpect(jsonPath("$.parameters[0].name", is("Weight")))
				.andExpect(jsonPath("$.parameters[0].entries[0].value", is(100.0)))
				.andExpect(jsonPath("$.entryDates[0]", is("2026-01-01T06:00:00")));
		
	}

}
