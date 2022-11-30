package lt.codeacademy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc //TODO 1
class AppTests {

	@Autowired  //TODO 2
	private MockMvc mockMvc;
	
	@Test //TODO 4
	void indexWhenUnAuthenticatedThenRedirect() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/index"))
				.andExpect(status().isUnauthorized());
		// @formatter:on
	}
	
	@Test
	@WithMockUser //TODO 3
	void indexWhenAuthenticatedThenOk() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/items/index"))
				.andExpect(status().isOk());
		// @formatter:on
	}

}