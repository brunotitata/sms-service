package br.com.sms.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientCreditsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InsufficientCreditsException(String msg) {
	super(msg);
    }

}
