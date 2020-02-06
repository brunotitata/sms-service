package br.com.sms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.dto.SmsDTO;
import br.com.sms.model.SMS;
import br.com.sms.repository.SmsFilter;
import br.com.sms.service.SmsService;

@RestController
@RequestMapping("/api")
public class SmsController {

    private SmsService smsService;

    public SmsController(SmsService smsService) {
	this.smsService = smsService;
    }

    @PostMapping("/sms/send")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SMS> sendSMS(@RequestBody SmsDTO sms) {

	smsService.send(sms);

	return ResponseEntity.noContent().build();
    }

    @GetMapping("/sms/report")
    public ResponseEntity<List<SmsDTO>> generateReport(
	    @RequestParam(name = "startDate", required = true) String startDate,
	    @RequestParam(name = "endDate", required = true) String endDate,
	    @RequestParam(name = "cellphone", required = false) String cellphone,
	    @RequestParam(name = "message", required = false) String message,
	    @RequestParam(name = "customer", required = false) String nameCustomer) {

	return ResponseEntity
		.ok(smsService.smsReport(new SmsFilter(startDate, endDate, cellphone, message, nameCustomer)));
    }

}
