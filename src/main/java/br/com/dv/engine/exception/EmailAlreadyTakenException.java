package br.com.dv.engine.exception;

public class EmailAlreadyTakenException extends RuntimeException {

    private static final String EXCEPTION_TEMPLATE = "E-mail %s already taken.";

    public EmailAlreadyTakenException(String email) {
        super(String.format(EXCEPTION_TEMPLATE, email));
    }

}
