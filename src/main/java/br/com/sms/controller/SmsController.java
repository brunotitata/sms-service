package br.com.sms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sms.DTO.SmsDTO;
import br.com.sms.model.SMS;
import br.com.sms.service.SmsService;

@RestController
@RequestMapping("/api")
public class SmsController {

    private SmsService smsService;

    public SmsController(SmsService smsService) {
	this.smsService = smsService;
    }

    @PostMapping("/send")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SMS> sendSMS(@RequestBody SmsDTO sms) {

	smsService.send(sms);

	return ResponseEntity.noContent().build();
    }

}
