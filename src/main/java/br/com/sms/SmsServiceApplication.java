package br.com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsServiceApplication {

//    @Autowired
//    private UserRepository repository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;

    public static void main(String[] args) {
	SpringApplication.run(SmsServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner run() throws Exception {
//	return args -> {
//
//	    if (!repository.findByEmail("brunotitata@gmail.com").isPresent()) {
//		User user = new User("Bruno Costa", "Estabelecimento Teste", "brunotitata@gmail.com",
//			encoder.encode("degauss123"), 1000, true);
//		Role role = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
//		user.setRoles(Collections.singleton(role));
//		repository.save(user);
//	    }
//
//	};
//
//    }
}
