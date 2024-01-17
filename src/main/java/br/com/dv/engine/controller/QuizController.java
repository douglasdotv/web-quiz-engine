package br.com.dv.engine.controller;

import br.com.dv.engine.dto.AnswerSubmissionRequest;
import br.com.dv.engine.dto.AnswerSubmissionResponse;
import br.com.dv.engine.dto.QuizRequest;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.service.QuizService;
import jakarta.validation.Valid;
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
    public ResponseEntity<QuizResponse> addQuiz(@RequestBody @Valid QuizRequest request) {
        QuizResponse response = quizService.addQuiz(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(@PathVariable Integer id,
                                                                 @RequestBody AnswerSubmissionRequest request) {
        AnswerSubmissionResponse response = quizService.submitAnswer(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Integer id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

}
