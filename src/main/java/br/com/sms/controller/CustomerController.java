package br.com.sms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

	Customer customer = customerService.newCustomer(customerDTO);

	return ResponseEntity.created(
		ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri())
		.build();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> find(@PathVariable String id) {
	return ResponseEntity.ok(customerService.findById(UUID.fromString(id)));
    }

    @GetMapping("/customer")
    public ResponseEntity<Page<Customer>> findAllCustomer(@RequestParam("user") String id, Pageable pageable) {
	return ResponseEntity.ok(customerService.findAllCustomerByUser(UUID.fromString(id), pageable));
    }

    @GetMapping("/find/customer")
    public ResponseEntity<List<Customer>> customer(@RequestParam(name = "name", required = false) String name,
	    @RequestParam(name = "cellphone", required = false) String cellphone) {

	return ResponseEntity.ok(customerService.customer(name, cellphone));
    }

    @DeleteMapping("/customer/{id}/delete")
    public ResponseEntity<Void> removeCustomer(@PathVariable("id") String customerId) {

	customerService.removeCustomer(UUID.fromString(customerId));

	return ResponseEntity.noContent().build();
    }

}
