package mg.healthpoint.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.healthpoint.dto.SaveAccountRequest;
import mg.healthpoint.dto.SavePatientRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PatientRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"Patient"})
	public void test_getPatient() throws Exception {
		
		mockMvc.perform(get("/api/patient"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.account.firstname", is("John")))
				.andExpect(jsonPath("$.room", is("26C")))
				.andExpect(jsonPath("$.parameters[0].name", is("Weight")))
				.andExpect(jsonPath("$.parameters[0].entries[0].value", is(100.0)))
				.andExpect(jsonPath("$.entryDates[0]", is("2026-01-01T06:00:00")));
		
	}
	
	@Test
	@WithMockUser(username = "ntsoaran", roles = {"Staff"})
	public void test_getPatients() throws Exception {
		
		mockMvc.perform(get("/api/patients"))
				.andExpect(status().isOk());
		
	}
	
	@Test
	@WithMockUser(username = "ntsoaran", roles = {"Staff"})
	public void test_getPatientById() throws Exception {
		
		mockMvc.perform(get("/api/patients/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.account.firstname", is("John")))
				.andExpect(jsonPath("$.room", is("26C")))
				.andExpect(jsonPath("$.parameters[0].name", is("Weight")))
				.andExpect(jsonPath("$.parameters[0].entries[0].value", is(100.0)))
				.andExpect(jsonPath("$.entryDates[0]", is("2026-01-01T06:00:00")));
		
	}
	
	@Test
	@WithMockUser(username = "ntsoaran", roles = {"Staff"})
	public void test_savePatient() throws Exception {
		
		mockMvc.perform(post("/api/save-patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(new SavePatientRequest(
						1, "25B", "Nephrotic Syndrome", 
						new SaveAccountRequest(
								"johndoe", "pwdJohn","John", "Doe", "Male", 
								LocalDate.of(1996, 11, 11), 
								"Itaosy Cité Akany Sambatra Lot B29, Antananarivo, Analamanga, Madagascar",
								"033 62 667 74 / johndoe@example.com")))))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", is(1)));
		
	}

}
