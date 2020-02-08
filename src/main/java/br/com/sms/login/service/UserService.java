package br.com.sms.login.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sms.login.config.jwt.JwtProvider;
import br.com.sms.login.dto.LoginDTO;
import br.com.sms.login.dto.RegisterDTO;
import br.com.sms.login.exception.ExistingEmailException;
import br.com.sms.login.exception.IllegalRoleException;
import br.com.sms.login.model.AccessToken;
import br.com.sms.login.model.Role;
import br.com.sms.login.model.Role.RoleName;
import br.com.sms.login.model.User;
import br.com.sms.login.repository.role.RoleRepository;
import br.com.sms.login.repository.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(RegisterDTO registerData) {

	if (userRepository.existsByEmail(registerData.getEmail())) {
	    throw new ExistingEmailException("Fail -> Email is already in use!");
	}

	User user = new User(registerData.getName(), registerData.getEstablishment(), registerData.getEmail(),
		encoder.encode(registerData.getPassword()), registerData.getCredit());

	Set<String> strRoles = registerData.getRole();
	Set<Role> roles = new HashSet<>();

	strRoles.forEach(role -> {
	    switch (role) {
	    case "admin":
		Role admin = roleRepository.findByName(RoleName.ROLE_ADMIN)
			.orElseThrow(() -> new IllegalRoleException("Fail! -> Cause: Admin Role not find."));
		roles.add(admin);

		break;
	    case "user":
		Role custodiante = roleRepository.findByName(RoleName.ROLE_USER)
			.orElseThrow(() -> new IllegalRoleException("Fail! -> Cause: User Role not find."));
		roles.add(custodiante);

		break;
	    default:
		throw new IllegalRoleException("Fail! -> Cause: Role invalid.");
	    }
	});

	user.setRoles(roles);
	user.setCredit(registerData.getCredit() == null ? 0 : registerData.getCredit());
	return userRepository.save(user);

    }

    public AccessToken authenticateUser(LoginDTO loginDto) {

	Authentication authentication = authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

	SecurityContextHolder.getContext().setAuthentication(authentication);

	return new AccessToken(jwtProvider.generateJwtToken(authentication));

    }
}
