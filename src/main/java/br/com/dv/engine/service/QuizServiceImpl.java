package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private static final String QUIZ_TITLE = "The Java Logo";
    private static final String QUIZ_TEXT = "What is depicted on the Java logo?";
    private static final List<String> QUIZ_OPTIONS = List.of("Robot", "Tea leaf", "Cup of coffee", "Bug");
    private static final Integer CORRECT_ANSWER_INDEX = 2;
    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please try again.";

    @Override
    public QuizResponse getQuiz() {
        return new QuizResponse(QUIZ_TITLE, QUIZ_TEXT, QUIZ_OPTIONS);
    }

    @Override
    public AnswerSubmissionResponse submitAnswer(Integer answerIndex) {
        boolean isCorrect = answerIndex != null && answerIndex.equals(CORRECT_ANSWER_INDEX);
        String feedback = isCorrect ? CORRECT_FEEDBACK : INCORRECT_FEEDBACK;
        return new AnswerSubmissionResponse(isCorrect, feedback);
    }

}
