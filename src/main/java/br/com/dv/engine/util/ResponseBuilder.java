package br.com.dv.engine.util;

import br.com.dv.engine.dto.PaginatedQuizResponse;
import br.com.dv.engine.dto.QuizResponse;
import br.com.dv.engine.entity.Quiz;
import org.springframework.data.domain.Page;

import java.util.List;

public final class ResponseBuilder {

    public static final String UTILITY_CLASS_WARNING = "Utility class cannot be instantiated.";

    private ResponseBuilder() {
        throw new UnsupportedOperationException(UTILITY_CLASS_WARNING);
    }

    public static PaginatedQuizResponse buildPaginatedQuizResponse(Page<Quiz> quizzes) {
        List<QuizResponse> content = quizzes.getContent().stream()
                .map(quiz -> new QuizResponse(quiz.getId(), quiz.getTitle(), quiz.getText(), quiz.getOptions()))
                .toList();

        return new PaginatedQuizResponse(
                quizzes.getTotalPages(),
                quizzes.getTotalElements(),
                quizzes.isLast(),
                quizzes.isFirst(),
                quizzes.getSort(),
                quizzes.getNumber(),
                quizzes.getNumberOfElements(),
                quizzes.getSize(),
                quizzes.isEmpty(),
                quizzes.getPageable(),
                content
        );
    }

}
