package br.com.sms.repository.establishment;

import java.util.Set;
import java.util.UUID;

import br.com.sms.model.Establishment;

public interface EstablishmentRepository {

    Establishment save(Establishment establishment);

    void deleteAll();

    Establishment buscarEstabelecimento(UUID id);

    Set<Establishment> saveAll(Set<Establishment> establishments);

}
