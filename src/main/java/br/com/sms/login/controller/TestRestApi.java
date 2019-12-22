package br.com.sms.login.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestApi {

    @GetMapping("/api/test/custodiante")
    @PreAuthorize("hasRole('CUSTODIANTE')")
    public String userCustodiante() {
        return ">>> Custodiante !";
    }

    @GetMapping("/api/test/auditor")
    @PreAuthorize("hasRole('AUDITOR')")
    public String userAuditor() {
        return ">>> Auditor !";
    }

    @GetMapping("/api/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String userAdmin() {
        return ">>> Admin !";
    }

}
