package br.com.sms.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.repository.role.RoleRepository;

@Configuration
public class ApplicationConfig {

    private RoleRepository roleRepository;

    public ApplicationConfig(RoleRepository roleRepository) {
	this.roleRepository = roleRepository;
    }

    @Bean
    public void createRoleWhenInitializing() {

	for (RoleName roleName : RoleName.values()) {
	    if (!roleRepository.findByName(roleName).isPresent()) {
		roleRepository.save(new Role(roleName));
	    } else {
		continue;
	    }
	}
    }

}
