package br.com.sms.smsservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.sms.smsservice.AbstractTests;

@SpringBootTest
public class CustomerControllerTest extends AbstractTests{

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void findAllCustomer() throws Exception {

	mockMvc.perform(get("/api/customer")
		.queryParam("user", "46c2c662-d900-4f4f-9520-036c0a7ce1e9"))
		.andExpect(jsonPath("$.content.[0].name", is("Borracheiro do João")))
		.andExpect(jsonPath("$.content.[0].cellPhone", is("2222222222")))
		.andExpect(jsonPath("$.content.[0].email", is("borracheiro@loja.com")))
		.andExpect(jsonPath("$.content.[0].counterSms", is(0)))
		.andExpect(jsonPath("$.content.[1].name", is("Farmacia do Seizi")))
		.andExpect(jsonPath("$.content.[1].cellPhone", is("12121212121")))
		.andExpect(jsonPath("$.content.[1].email", is("seizi@farmacia.com")))
		.andExpect(jsonPath("$.content.[1].counterSms", is(0)))
		.andExpect(status().isOk());
    }

}
