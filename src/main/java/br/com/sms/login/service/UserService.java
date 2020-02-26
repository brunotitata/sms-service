package br.com.sms.login.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sms.login.config.jwt.JwtProvider;
import br.com.sms.login.dto.LoginDTO;
import br.com.sms.login.dto.RegisterDTO;
import br.com.sms.login.exception.UserExistingException;
import br.com.sms.login.model.AccessToken;
import br.com.sms.model.Customer;
import br.com.sms.model.Employee;
import br.com.sms.model.Establishment;
import br.com.sms.model.EstablishmentId;
import br.com.sms.model.SMS;
import br.com.sms.model.User;
import br.com.sms.model.UserId;
import br.com.sms.repository.establishment.EstablishmentRepository;
import br.com.sms.repository.user.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private EstablishmentRepository establishmentRepository;
    private PasswordEncoder encoder;
    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository clientRepository, EstablishmentRepository establishmentRepository,
	    PasswordEncoder encoder, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
	this.userRepository = clientRepository;
	this.establishmentRepository = establishmentRepository;
	this.encoder = encoder;
	this.jwtProvider = jwtProvider;
	this.authenticationManager = authenticationManager;
    }

    public User registerUser(RegisterDTO registerData) {

	if (registerData == null)
	    throw new RuntimeException("Objeto Usuario não pode ser nulo.");

	if (userRepository.existClient(registerData.getCpf())) {
	    throw new UserExistingException("Error -> Cliente já cadastrado com CPF: " + registerData.getCpf());
	}

	userRepository.findByEmail(registerData.getEmail()).ifPresent(user -> {
	    throw new UserExistingException("Error -> Cliente já cadastrado com EMAIL: " + registerData.getEmail());
	});

	List<SMS> sms = Collections.emptyList();
	Set<Customer> customer = Collections.emptySet();
	Set<Employee> employee = Collections.emptySet();

	return userRepository.save(new User(new UserId(UUID.randomUUID()), registerData.getNome(),
		registerData.getCelular(), registerData.getCpf(), registerData.getEmail(),
		encoder.encode(registerData.getPassword()),

		establishmentRepository.save(new Establishment(new EstablishmentId(UUID.randomUUID()),
			registerData.getEstablishment().getNome(), registerData.getEstablishment().getEndereco(),
			registerData.getEstablishment().getCnpj(), employee, sms, customer))));

    }

    public AccessToken authenticateUser(LoginDTO loginDto) {

	Authentication authentication = authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

	SecurityContextHolder.getContext().setAuthentication(authentication);

	return new AccessToken(jwtProvider.generateJwtToken(authentication));

    }
}
