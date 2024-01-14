package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    private static final Integer CORRECT_ANSWER_INDEX = 2;
    private static final String CORRECT_FEEDBACK = "Congratulations, you're right!";
    private static final String INCORRECT_FEEDBACK = "Wrong answer! Please try again.";

    @Override
    public AnswerSubmissionResponse submitAnswer(Integer answerIndex) {
        boolean isCorrect = answerIndex != null && answerIndex.equals(CORRECT_ANSWER_INDEX);
        String feedback = isCorrect ? CORRECT_FEEDBACK : INCORRECT_FEEDBACK;
        return new AnswerSubmissionResponse(isCorrect, feedback);
    }

}
