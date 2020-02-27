package br.com.sms;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.Active;
import br.com.sms.model.Customer;
import br.com.sms.model.CustomerId;
import br.com.sms.model.Employee;
import br.com.sms.model.EmployeeId;
import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.model.UserId;
import br.com.sms.repository.customer.CustomerRepository;
import br.com.sms.repository.establishment.EstablishmentRepository;
import br.com.sms.repository.user.UserRepository;

@SpringBootApplication
public class SmsServiceApplication {

    private UserRepository userRepository;
    private EstablishmentRepository establishmentRepository;
    private CustomerRepository customerRepository;
    private PasswordEncoder password;

    public SmsServiceApplication(UserRepository userRepository, EstablishmentRepository establishmentRepository,
	    CustomerRepository customerRepository, PasswordEncoder password) {
	this.userRepository = userRepository;
	this.establishmentRepository = establishmentRepository;
	this.customerRepository = customerRepository;
	this.password = password;
    }

    public static void main(String[] args) {
	SpringApplication.run(SmsServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() throws Exception {
	return args -> {

	    Optional<User> userAdmin = userRepository.findUserByUserId("ad416334-e578-4181-8c2f-75e1859a4f1e");

	    if (!userAdmin.isPresent()) {

		List<SMS> sms = Collections.emptyList();
		Set<Customer> customers = Collections.emptySet();
		Set<Employee> employees = Collections.emptySet();

		Establishment estabelecimento = establishmentRepository.save(
			new Establishment(new EstablishmentId(UUID.fromString("985ebcc1-8f28-4312-a718-37d88ccd6063")),
				"Tecnologia de Software", "Arnaldo Victaliano 130", "06100428000192", employees, sms,
				customers));

		userRepository.save(new User(new UserId(UUID.fromString("ad416334-e578-4181-8c2f-75e1859a4f1e")),
			"Bruno Costa", "16991034148", "38441868832", "brunotitata@gmail.com", password.encode("123456"),
			estabelecimento));

		User user = userRepository.findUserByUserId("ad416334-e578-4181-8c2f-75e1859a4f1e")
			.orElseThrow(() -> new ArgumentInvalidException(
				"Cliente não encontrado com ID: " + "ad416334-e578-4181-8c2f-75e1859a4f1e"));

		Establishment establishment = user.getEstablishment();

		establishment.setEmployee(Stream.of(
			new Employee(new EmployeeId(UUID.randomUUID()), "Rodrigo", "rodrigo@gmail.com", "123456",
				Active.ATIVO, establishment),
			new Employee(new EmployeeId(UUID.randomUUID()), "Leandro", "leandro@gmail.com", "123456",
				Active.ATIVO, establishment))
			.collect(Collectors.toSet()));

		customerRepository
			.save(new Customer(new CustomerId(UUID.fromString("bfbaeba5-0e0c-4150-b239-fd457c6937a3")),
				"Tiago TI LTDA", "16991034148", "tiago@gmail.com", establishment));

		establishmentRepository.save(establishment);
		userRepository.save(user);
	    }
	};

    }
}
