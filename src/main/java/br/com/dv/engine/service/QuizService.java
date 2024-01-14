package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;

import java.util.List;

public interface QuizService {

    List<QuizResponse> getAllQuizzes();

    QuizResponse getQuizById(Integer id);

    QuizResponse addQuiz(QuizRequest quiz);

    AnswerSubmissionResponse submitAnswer(Integer answerIndex);

}
