package br.com.sms.repository.establishment;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;

public interface EstablishmentRepositorySpringData extends JpaRepository<Establishment, Long> {

    Optional<Establishment> findByEstablishmentId(EstablishmentId establishmentId);

}
