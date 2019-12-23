package br.com.sms.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFound extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomerNotFound(String msg) {
	super(msg);
    }

}
