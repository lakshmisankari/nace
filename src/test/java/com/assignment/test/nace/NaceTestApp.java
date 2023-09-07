package com.assignment.test.nace;

/**
 * @author Lakshmi Subbiah
 *
 */
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.assignment.nace.NaceApplication;
import com.assignment.nace.NaceController;
import com.assignment.nace.NaceDetails;
import com.assignment.nace.NaceHelper;
import com.assignment.nace.NaceService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { NaceApplication.class, NaceController.class, NaceService.class, NaceHelper.class,
		NaceDetails.class })
public class NaceTestApp extends NaceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testNace() throws Exception {

		mockMvc.perform(get("/nace/getDetails/398481")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$.order1").value("398481"))
				.andExpect(jsonPath("$.level").value("1")).andExpect(jsonPath("$.code").value("A"))
				.andExpect(jsonPath("$.parent").value(""))
				.andExpect(jsonPath("$.description").value("AGRICULTURE, FORESTRY AND FISHING"))
				.andExpect(jsonPath("$.itemincludes").value(
						"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats."))
				.andExpect(jsonPath("$.itemalsoincludes").value("")).andExpect(jsonPath("$.rulings").value(""))
				.andExpect(jsonPath("$.itemexcludes").value("")).andExpect(jsonPath("$.reference").value("A"));

	}

}
