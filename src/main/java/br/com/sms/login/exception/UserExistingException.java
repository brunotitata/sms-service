package br.com.sms.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserExistingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserExistingException(String msg) {
	super(msg);
    }

}
