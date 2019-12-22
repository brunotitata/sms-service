package br.com.sms.login.repository.role;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;

@Repository
public class RoleRepositoryJpa implements RoleRepository {

    private RoleRepositorySpringData repositorySpringData;

    public RoleRepositoryJpa(RoleRepositorySpringData repositorySpringData) {
	this.repositorySpringData = repositorySpringData;
    }

    @Override
    public Optional<Role> findByName(RoleName roleName) {
	return repositorySpringData.findByName(roleName);
    }

    @Override
    public Role save(Role role) {
	return repositorySpringData.save(role);
    }

}
