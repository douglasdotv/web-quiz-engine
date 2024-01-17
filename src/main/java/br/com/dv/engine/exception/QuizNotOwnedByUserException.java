package br.com.dv.engine.exception;

public class QuizNotOwnedByUserException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE =
            "Cannot delete a quiz that is not authored by the currently authenticated user.";

    public QuizNotOwnedByUserException() {
        super(EXCEPTION_MESSAGE);
    }

}
