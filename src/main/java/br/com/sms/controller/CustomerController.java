package br.com.sms.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sms.dto.CustomerDTO;
import br.com.sms.dto.NewCustomerDTO;
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
    public ResponseEntity<Customer> newCustomer(@RequestBody NewCustomerDTO customerDTO) {

	Customer customer = customerService.newCustomer(customerDTO);

	return ResponseEntity.created(
		ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri())
		.build();
    }

    @GetMapping("/customer")
    public ResponseEntity<Page<CustomerDTO>> findAllCustomer(@RequestParam("cpf") String cpf, Pageable pageable) {
	return ResponseEntity.ok(customerService.findAllCustomerByUserCpf(cpf, pageable));
    }

    @DeleteMapping("/customer")
    public ResponseEntity<Void> removeCustomer(@RequestParam(name = "cpf", required = true) String userCpf,
	    @RequestParam(name = "cellphone", required = true) String cellphone) {

	customerService.removeCustomer(userCpf, cellphone);

	return ResponseEntity.noContent().build();
    }

    @PutMapping("/customer")
    public ResponseEntity<Void> editCustomer(@RequestParam(name = "cpf", required = true) String userCpf,
	    @RequestParam(name = "cellphone", required = true) String cellphone, @RequestBody CustomerDTO customerDTO) {

	customerService.editCustomer(userCpf, cellphone, customerDTO);

	return ResponseEntity.noContent().build();
    }

}
