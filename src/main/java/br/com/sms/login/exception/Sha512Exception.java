package br.com.sms.login.exception;

public class Sha512Exception extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public Sha512Exception(String msg) {
        super(msg);
    }

}
