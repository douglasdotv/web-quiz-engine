package br.com.dv.engine.controller;

import br.com.dv.engine.dto.*;
import br.com.dv.engine.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/quizzes")
@RestController
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<PaginatedQuizResponse> getAllQuizzes(@RequestParam(defaultValue = "0") Integer page) {
        PaginatedQuizResponse response = quizService.getAllQuizzes(page);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Integer id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizResponse> updateQuizById(@PathVariable Integer id,
                                                       @RequestBody @Valid QuizRequest request) {
        QuizResponse response = quizService.updateQuizById(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerSubmissionResponse> submitAnswer(@PathVariable Integer id,
                                                                 @RequestBody AnswerSubmissionRequest request) {
        AnswerSubmissionResponse response = quizService.submitAnswer(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/completed")
    public ResponseEntity<PaginatedCompletedQuizResponse> getCompletedQuizzes(
            @RequestParam(defaultValue = "0") Integer page
    ) {
        PaginatedCompletedQuizResponse response = quizService.getCompletedQuizzes(page);
        return ResponseEntity.ok(response);
    }

}
