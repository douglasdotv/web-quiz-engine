package br.com.dv.engine.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class QuizExceptionHandler {

    public record CustomError(HttpStatus status, String message, String path, Instant timestamp) {
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return getResponseEntity(HttpStatus.BAD_REQUEST, errorMessages.toString(), request);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<CustomError> handleQuizNotFound(QuizNotFoundException ex,
                                                          HttpServletRequest request) {
        return getResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(DuplicateAnswerIndicesException.class)
    public ResponseEntity<CustomError> handleDuplicateAnswerIndices(DuplicateAnswerIndicesException ex,
                                                                    HttpServletRequest request) {
        return getResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<CustomError> handleEmailAlreadyTaken(EmailAlreadyTakenException ex,
                                                               HttpServletRequest request) {
        return getResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    private ResponseEntity<CustomError> getResponseEntity(HttpStatus status, String message,
                                                          HttpServletRequest request) {
        CustomError error = new CustomError(status, message, request.getRequestURI(), Instant.now());
        return new ResponseEntity<>(error, status);
    }

}
