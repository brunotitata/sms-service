package br.com.sms.login.config.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.sms.login.DTOs.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${security.app.jwtSecret}")
    private String jwtSecret;

    @Value("${security.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

	UserDTO userDTO = (UserDTO) authentication.getPrincipal();

	return Jwts.builder().setSubject((userDTO.getEmail())).setIssuedAt(new Date())
		.claim("uuid", userDTO.getId())
		.claim("username", userDTO.getName())
		.claim("lastName", userDTO.getLastName())
		.claim("credit", userDTO.getCredit())
		.setExpiration(new Date((new Date()).getTime() + jwtExpiration))
		.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getUserNameFromJwtToken(String token) {
	return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Claims getUserInformation(String token) {
	return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public boolean validateJwtToken(String authToken) {
	try {
	    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
	    return true;
	} catch (MalformedJwtException e) {
	    logger.error("Invalid JWT token -> Message: {}", e);
	} catch (ExpiredJwtException e) {
	    logger.error("Expired JWT token -> Message: {}", e);
	} catch (UnsupportedJwtException e) {
	    logger.error("Unsupported JWT token -> Message: {}", e);
	} catch (IllegalArgumentException e) {
	    logger.error("JWT claims string is empty -> Message: {}", e);
	}

	return false;
    }

}
