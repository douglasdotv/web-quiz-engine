package br.com.dv.engine.controller;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/quizzes")
@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(@RequestParam(name = "answer") Integer answerIndex) {
        AnswerSubmissionResponse response = quizService.submitAnswer(answerIndex);
        return ResponseEntity.ok(response);
    }

}
