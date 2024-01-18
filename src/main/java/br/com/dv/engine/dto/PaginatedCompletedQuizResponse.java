package br.com.dv.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PaginatedCompletedQuizResponse(
        int totalPages,
        long totalElements,
        boolean last,
        boolean first,
        boolean empty,
        @JsonProperty("content")
        List<CompletedQuizResponse> completedQuizzes
) {
}
