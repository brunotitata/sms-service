package br.com.sms.repository.establishment;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;

@Repository
public class EstablishmentRepositoryJpa implements EstablishmentRepository {

    private EstablishmentRepositorySpringData establishmentRepositorySpringData;

    public EstablishmentRepositoryJpa(EstablishmentRepositorySpringData establishmentRepositorySpringData) {
	this.establishmentRepositorySpringData = establishmentRepositorySpringData;
    }

    @Override
    public Establishment save(Establishment establishment) {
	return establishmentRepositorySpringData.save(establishment);
    }

    @Override
    public Set<Establishment> saveAll(Set<Establishment> establishments) {
	return new HashSet<>(establishmentRepositorySpringData.saveAll(establishments));

    }

    @Override
    public void deleteAll() {
	establishmentRepositorySpringData.deleteAll();

    }

    @Override
    public Establishment buscarEstabelecimento(UUID id) {
	return establishmentRepositorySpringData.findByEstablishmentId(new EstablishmentId(id))
		.orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado com ID: " + id));
    }

}
