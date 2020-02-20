//package br.com.sms.smsservice.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import br.com.sms.model.Establishment;
//import br.com.sms.model.EstablishmentId;
//import br.com.sms.repository.establishment.EstablishmentRepository;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class EstablishmentRepositoryTest {
//
//    @Autowired
//    private EstablishmentRepository establishmentRepository;
//
//    @BeforeEach
//    public void setUp() {
//	establishmentRepository.findEstablishment(new EstablishmentId("b116dca4-7f9f-47fc-bba2-c41ed46061ec"));
//    }
//
//    @Test
//    public void save() {
//
//	establishmentRepository.save(new Establishment(new EstablishmentId("b116dca4-7f9f-47fc-bba2-c41ed46061ec"),
//		"Arley", "Leopoldo", "68.861.290/0001-00", null, null, null));
//
//    }
//
//}
