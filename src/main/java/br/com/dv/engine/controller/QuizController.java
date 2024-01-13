package br.com.dv.engine.controller;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/quiz")
@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<QuizResponse> getQuiz() {
        QuizResponse response = quizService.getQuiz();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(@RequestParam(name = "answer") Integer answerIndex) {
        AnswerSubmissionResponse response = quizService.submitAnswer(answerIndex);
        return ResponseEntity.ok(response);
    }

}
