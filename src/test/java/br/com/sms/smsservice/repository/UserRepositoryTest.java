package br.com.sms.smsservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sms.model.Customer;
import br.com.sms.model.Employee;
import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.model.UserId;
import br.com.sms.repository.establishment.EstablishmentRepository;
import br.com.sms.repository.user.UserRepository;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EstablishmentRepository establishmentRepository;

    protected User user;

	@BeforeEach
    public void setUp() {

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customer = Collections.emptySet();
	Set<Employee> employees = Collections.emptySet();

	Establishment estabelecimento = establishmentRepository
		.save(new Establishment(new EstablishmentId(UUID.fromString("0e488aac-1a68-412a-baad-e1319a49fd01")),
			"Arley Chopão", "Leopoldo Carlos de Oliveira 350", "06100428000192", employees, sms, customer));

	user = userRepository.save(new User(new UserId(UUID.fromString("46c2c662-d900-4f4f-9520-036c0a7ce1e9")),
		"Arley", "16991034148", "38441868832", "arley@arley.com", "123456", estabelecimento));

    }

    @AfterEach
    public void tearDown() {
	userRepository.delete("46c2c662-d900-4f4f-9520-036c0a7ce1e9");
    }

    @Test
    public void newUserWithEstablishment() {

	assertEquals("Arley", user.getNome());
	assertEquals("16991034148", user.getCelular());
	assertEquals("38441868832", user.getCpf());
	assertEquals("arley@arley.com", user.getEmail());

	assertEquals("Arley Chopão", user.getEstablishment().getNome());
	assertEquals("Leopoldo Carlos de Oliveira 350", user.getEstablishment().getEndereco());
	assertEquals("06100428000192", user.getEstablishment().getCnpj());

	assertEquals(0, user.getEstablishment().getEmployee().size());
	assertEquals(0, user.getEstablishment().getCustomer().size());
	assertEquals(0, user.getEstablishment().getSms().size());

    }

    @Test
    public void findUserByUserId() {

	User user = userRepository.findUserByUserId("46c2c662-d900-4f4f-9520-036c0a7ce1e9").get();

	assertEquals("46c2c662-d900-4f4f-9520-036c0a7ce1e9", user.getUserId().getId().toString());
	assertEquals("Arley", user.getNome());
	assertEquals("16991034148", user.getCelular());
	assertEquals("38441868832", user.getCpf());
	assertEquals("arley@arley.com", user.getEmail());
	assertEquals("123456", user.getPassword());

	assertEquals("0e488aac-1a68-412a-baad-e1319a49fd01",
		user.getEstablishment().getEstablishmentId().getId().toString());
	assertEquals("Arley Chopão", user.getEstablishment().getNome());
	assertEquals("Leopoldo Carlos de Oliveira 350", user.getEstablishment().getEndereco());
	assertEquals("06100428000192", user.getEstablishment().getCnpj());
    }

}
