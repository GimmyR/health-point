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
import mg.healthpoint.dto.SaveStaffRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StaffRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@WithMockUser(username = "johndoe", roles = {"Patient"})
	public void test_isStaff() throws Exception {
		
		mockMvc.perform(get("/api/is-staff"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", is(false)));
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"Staff"})
	public void test_saveStaff() throws Exception {
		
		mockMvc.perform(post("/api/staff/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(new SaveStaffRequest(
					null,
					new SaveAccountRequest(
							"marysue", "pwdMary","Mary", "Sue", "Female", 
							LocalDate.of(1996, 11, 11), 
							"Itaosy Cité Akany Sambatra Lot B29, Antananarivo, Analamanga, Madagascar",
							"033 62 667 74 / marysue@example.com"),
					null
				)))).andExpect(status().isCreated())
					.andExpect(jsonPath("$", is(3)));
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"Staff"})
	public void test_findAllStaffs() throws Exception {
		
		mockMvc.perform(get("/api/staffs"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].username", is("ntsoaran")));
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"Staff"})
	public void test_findUniqueStaff() throws Exception {
		
		mockMvc.perform(get("/api/staffs/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.account.username", is("ntsoaran")));
		
	}
	
	// tester isAdmin
	
	// tester removeStaff

}
