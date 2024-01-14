package br.com.dv.engine.controller;

import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/quizzes")
@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuizzes() {
        List<QuizResponse> response = quizService.getAllQuizzes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Integer id) {
        QuizResponse response = quizService.getQuizById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<QuizResponse> addQuiz(@RequestBody QuizRequest request) {
        QuizResponse response = quizService.addQuiz(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(@PathVariable Integer id,
                                                                 @RequestParam(name = "answer") Integer answerIndex) {
        AnswerSubmissionResponse response = quizService.submitAnswer(id, answerIndex);
        return ResponseEntity.ok(response);
    }

}
