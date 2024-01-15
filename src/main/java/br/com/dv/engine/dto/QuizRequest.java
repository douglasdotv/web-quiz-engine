package br.com.dv.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record QuizRequest(
        String title,
        String text,
        List<String> options,
        @JsonProperty("answer")
        List<Integer> answerIndices
) {
}
