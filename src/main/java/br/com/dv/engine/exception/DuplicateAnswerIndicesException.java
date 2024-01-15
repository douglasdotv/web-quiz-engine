package br.com.dv.engine.exception;

public class DuplicateAnswerIndicesException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Duplicate answer indices are not allowed.";

    public DuplicateAnswerIndicesException() {
        super(EXCEPTION_MESSAGE);
    }

}
