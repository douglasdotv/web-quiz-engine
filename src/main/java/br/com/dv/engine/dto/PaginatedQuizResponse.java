package br.com.dv.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public record PaginatedQuizResponse(
        int totalPages,
        long totalElements,
        boolean last,
        boolean first,
        Sort sort,
        int number,
        int numberOfElements,
        int size,
        boolean empty,
        Pageable pageable,
        @JsonProperty("content")
        List<QuizResponse> quizzes
) {
}
