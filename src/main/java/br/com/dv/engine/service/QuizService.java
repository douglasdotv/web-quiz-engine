package br.com.dv.engine.service;

import br.com.dv.engine.dto.*;

public interface QuizService {

    PaginatedQuizResponse getAllQuizzes(Integer page);

    QuizResponse getQuizById(Integer id);

    QuizResponse addQuiz(QuizRequest quiz);

    void deleteQuizById(Integer id);

    QuizResponse updateQuizById(Integer id, QuizRequest quiz);

    AnswerSubmissionResponse submitAnswer(Integer id, AnswerSubmissionRequest answer);

}
