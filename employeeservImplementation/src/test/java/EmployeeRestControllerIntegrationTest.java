
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.paypal.bfs.test.employeeserv.EmployeeController;
import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EmployeeservApplication.class, EmployeeController.class })
// @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes =
// {EmployeeservApplication.class, EmployeeController.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
// @TestPropertySource(locations =
// "classpath:application-integrationtest.properties")
@AutoConfigureTestDatabase
public class EmployeeRestControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EmployeeRepository repository;

	@After
	public void resetDb() {
		repository.deleteAll();
	}

	@Test
	public void whenValidInput_thenCreateEmployee() throws IOException, Exception {
		String firstName = "KRISH";
		String lastName = "C";
		Employee e = createTestEmployee(firstName, lastName);

		/*
		 * MvcResult result = mvc.perform(MockMvcRequestBuilders
		 * .post("/v1/bfs/employees") .contentType(MediaType.APPLICATION_JSON)
		 * .content(JsonUtil.toJson(e)) .accept(MediaType.APPLICATION_JSON))
		 * .andReturn();
		 * 
		 * String content = result.getResponse().getContentAsString();
		 * System.out.println("content in response for Create is : "+content);
		 */

		mvc.perform(post("/v1/bfs/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(e))
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(firstName));

	}

	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
		Employee a = createTestEmployee("RAM", "B");
		Employee x = repository.saveAndFlush(a);
		Long empId = x.getId();
		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1").accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String content = result.getResponse().getContentAsString();
		System.out.println("content in response is : " + content);

		mvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/{id}", empId).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(MockMvcResultMatchers.status().isOk())
				// .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(empId))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("RAM"));

	}

	private Employee createTestEmployee(String firstName, String lastName) {
		
		Employee emp = new Employee();
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setBirthDate(new Date(1990, 10, 27));
		Address a = new Address();
		// a.setId(id.intValue());
		a.setLine1("123 st");
		a.setCity("Piscataway");
		a.setCountry("USA");
		a.setState("NJ");
		a.setZipcode("08820");
		emp.setAddress(a);
		emp.setId(123l);

		return emp;
	}

}