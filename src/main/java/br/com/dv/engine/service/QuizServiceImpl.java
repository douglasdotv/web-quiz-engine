package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.model.Quiz;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuizServiceImpl implements QuizService {

    private static final int INITIAL_QUIZ_ID = 1;
    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please try again.";
    private static final String DUPLICATE_ANSWER_INDICES_MESSAGE = "Duplicate answer indices are not allowed.";
    private static final String QUIZ_NOT_FOUND_MESSAGE = "Quiz not found.";

    private final AtomicInteger idCounter = new AtomicInteger(INITIAL_QUIZ_ID);
    private final Map<Integer, Quiz> quizzes = new ConcurrentHashMap<>();

    @Override
    public List<QuizResponse> getAllQuizzes() {
        return quizzes.values().stream()
                .map(quiz -> new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions()))
                .toList();
    }

    @Override
    public QuizResponse getQuizById(Integer id) {
        Quiz quiz = quizzes.get(id);

        if (quiz != null) {
            return new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, QUIZ_NOT_FOUND_MESSAGE);
    }

    @Override
    public QuizResponse addQuiz(QuizRequest quiz) {
        Set<Integer> uniqueAnswerIndices = getUniqueAnswerIndices(quiz.answerIndices());

        Quiz newQuiz = new Quiz();

        newQuiz.setId(idCounter.getAndIncrement());
        newQuiz.setTitle(quiz.title());
        newQuiz.setText(quiz.text());
        newQuiz.setOptions(quiz.options());
        newQuiz.setAnswerIndices(uniqueAnswerIndices);

        quizzes.put(newQuiz.getId(), newQuiz);

        return new QuizResponse(newQuiz.getId(), newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions());
    }

    @Override
    public AnswerSubmissionResponse submitAnswer(Integer id, Integer answerIndex) {
        Quiz quiz = quizzes.get(id);

        if (quiz != null) {
            boolean isCorrect = answerIndex != null && answerIndex.equals(quiz.getAnswerIndex());
            String feedback = isCorrect ? CORRECT_FEEDBACK : INCORRECT_FEEDBACK;
            return new AnswerSubmissionResponse(isCorrect, feedback);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, QUIZ_NOT_FOUND_MESSAGE);
    }

    private Set<Integer> getUniqueAnswerIndices(List<Integer> answerIndices) {
        if (answerIndices != null) {
            Set<Integer> uniqueAnswerIndices = new HashSet<>(answerIndices);
            if (uniqueAnswerIndices.size() != answerIndices.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, DUPLICATE_ANSWER_INDICES_MESSAGE);
            }
            return uniqueAnswerIndices;
        }
        return Collections.emptySet();
    }

}
