package mg.healthpoint.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.healthpoint.dto.SaveParameterRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ParameterRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	@WithMockUser(username = "ntsoaran", roles = {"Staff"})
	public void test_saveParameter() throws Exception {
		
		mockMvc.perform(post("/api/parameter/save")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsBytes(new SaveParameterRequest(null, 1, "Temperature", "*C", 35.0, 42.0))))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$", is(2)));
		
	}

}
