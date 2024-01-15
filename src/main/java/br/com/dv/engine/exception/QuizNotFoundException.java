package br.com.dv.engine.exception;

public class QuizNotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Quiz not found.";

    public QuizNotFoundException() {
        super(EXCEPTION_MESSAGE);
    }

}
