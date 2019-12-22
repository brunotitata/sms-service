package br.com.sms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.model.Customer;
import br.com.sms.service.CustomerService;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
	this.customerService = customerService;
    }

    @PostMapping("/api/customer")
    public ResponseEntity<Customer> newCustomer(@RequestBody CustomerDTO customerDTO) {

	return ResponseEntity.ok(customerService.newCustomer(customerDTO));
    }

}
