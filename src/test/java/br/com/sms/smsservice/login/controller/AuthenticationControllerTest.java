package br.com.sms.smsservice.login.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.role.RoleRepository;
import br.com.sms.login.repository.user.UserRepository;

@SpringBootTest
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User user;

    @BeforeEach
    public void setUp() {
	mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();

	user = new User("Bruno Costa", "Estabelecimento Teste", "brunotitata123@gmail.com", "123456", 0, true);
	Role role = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
	user.setRoles(Collections.singleton(role));
	userRepository.save(user);

    }

    @AfterEach
    public void tearDown() {

	Optional<User> brunotitata = userRepository.findByEmail("brunotitata1234@gmail.com");

	if (brunotitata.isPresent())
	    userRepository.delete(brunotitata.get());

	userRepository.delete(user);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void registerUserWithoutCredentialsShouldReturnStatus403() throws Exception {

	mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON_VALUE)
		.content("{\n" + "    \"name\": \"Bruno Costa\",\n"
			+ "    \"establishment\": \"Estabelecimento Teste\",\n"
			+ "    \"email\": \"brunotitata@gmail.com\",\n" + "    \"role\": [\"user\"],\n"
			+ "    \"password\": \"123456\"\n" + "}"))

		.andExpect(status().isForbidden());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void registerUserWithCredentialsAdminShouldReturnStatus201() throws Exception {

	mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON_VALUE)
		.content("{\n" + "    \"name\": \"Bruno Costa\",\n"
			+ "    \"establishment\": \"Estabelecimento Teste\",\n"
			+ "    \"email\": \"brunotitata1234@gmail.com\",\n" + "    \"role\": [\"admin\"],\n"
			+ "    \"password\": \"123456\"\n" + "}"))

		.andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenSavingExistingUserShouldReturnStatus409() throws Exception {

	mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON_VALUE)
		.content("{\n" + "    \"name\": \"Bruno Costa\",\n"
			+ "    \"establishment\": \"Estabelecimento Teste\",\n"
			+ "    \"email\": \"brunotitata123@gmail.com\",\n" + "    \"role\": [\"user\"],\n"
			+ "    \"password\": \"123456\"\n" + "}"))

		.andExpect(status().isConflict());

    }

}
