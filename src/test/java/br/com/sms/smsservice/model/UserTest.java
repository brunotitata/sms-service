package br.com.sms.smsservice.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import br.com.sms.login.exception.ArgumentInvalidException;
import br.com.sms.model.Customer;
import br.com.sms.model.Employee;
import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.model.UserId;

public class UserTest {

    @Test
    public void criandoUsuario() {

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customer = Collections.emptySet();
	Set<Employee> employee = Collections.emptySet();

	Establishment establishment = new Establishment(
		new EstablishmentId(UUID.fromString("23ede4a7-9219-4e88-8262-4cac0a3973e8")), "Arley Chopão",
		"Leopoldo Carlos de Oliveira 350", "68.861.290/0001-00", employee, sms, customer);

	User client = new User(new UserId(UUID.fromString("b116dca4-7f9f-47fc-bba2-c41ed46061ec")), "Arley",
		"16991034148", "384.418.688-32", "arley@chopao.com", "123456", establishment);

	assertEquals(UUID.fromString("b116dca4-7f9f-47fc-bba2-c41ed46061ec"), client.getUserId().getId());
	assertEquals("Arley", client.getNome());
	assertEquals("16991034148", client.getCelular());
	assertEquals("384.418.688-32", client.getCpf());
	assertEquals("arley@chopao.com", client.getEmail());
	assertEquals("123456", client.getPassword());

	assertEquals(UUID.fromString("23ede4a7-9219-4e88-8262-4cac0a3973e8"),
		client.getEstablishment().getEstablishmentId().getId());
	assertEquals("Arley Chopão", client.getEstablishment().getNome());
	assertEquals("Leopoldo Carlos de Oliveira 350", client.getEstablishment().getEndereco());
	assertEquals("68.861.290/0001-00", client.getEstablishment().getCnpj());
    }

    @Test
    public void validacaoEstablishment() {

	assertThatThrownBy(() -> new User(new UserId(UUID.fromString("b116dca4-7f9f-47fc-bba2-c41ed46061ec")), "Arley",
		"16991034148", "384.418.688-32", "arley@chopao.com", "123456", null))
			.isInstanceOf(ArgumentInvalidException.class).hasMessage(User.ERROR_ESTABELECIMENTO);

    }

    @Test
    public void validacaoClientId() {

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customer = Collections.emptySet();
	Set<Employee> employee = Collections.emptySet();

	Establishment establishment = new Establishment(
		new EstablishmentId(UUID.fromString("23ede4a7-9219-4e88-8262-4cac0a3973e8")), "Arley Chopão",
		"Leopoldo Carlos de Oliveira 350", "68.861.290/0001-00", employee, sms, customer);

	assertThatThrownBy(() -> new User(null, "Arley", "16991034148", "384.418.688-32", "arley@chopao.com", "123456",
		establishment)).isInstanceOf(ArgumentInvalidException.class).hasMessage(UserId.ERROR_USER_ID);
    }

}
