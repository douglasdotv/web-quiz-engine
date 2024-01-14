package br.com.dv.engine.service;

import br.com.dv.engine.dto.AnswerSubmissionResponse;

public interface QuizService {

    AnswerSubmissionResponse submitAnswer(Integer answerIndex);

}
