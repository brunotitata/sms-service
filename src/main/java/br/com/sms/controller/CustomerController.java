package br.com.sms.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.DTO.CustomerDTO;
import br.com.sms.model.Customer;
import br.com.sms.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
	this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> newCustomer(@RequestBody CustomerDTO customerDTO) {

	return ResponseEntity.ok(customerService.newCustomer(customerDTO));
    }

    @GetMapping("/customer")
    public ResponseEntity<Page<Customer>> findAllCustomer(@RequestParam("user") String id) {

	return ResponseEntity.ok(customerService.findAllCustomerByUser(UUID.fromString(id)));
    }

}
