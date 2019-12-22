package br.com.sms.login.repository.role;

import java.util.Optional;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;

public interface RoleRepository {

    Optional<Role> findByName(RoleName roleName);

    Role save(Role role);

}
