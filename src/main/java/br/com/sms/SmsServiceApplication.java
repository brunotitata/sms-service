package br.com.sms;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.role.RoleRepository;
import br.com.sms.login.repository.user.UserRepository;

@SpringBootApplication
public class SmsServiceApplication {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
	SpringApplication.run(SmsServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
	return args -> {

	    if (!repository.findByEmail("brunotitata@gmail.com").isPresent()) {
		User user = new User("Bruno Costa", "Estabelecimento Teste", "brunotitata@gmail.com",
			encoder.encode("123456"), 1000, true);
		Role role = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
		user.setRoles(Collections.singleton(role));
		repository.save(user);
	    }

	};

    }
}
