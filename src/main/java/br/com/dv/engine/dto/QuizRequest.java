package br.com.dv.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record QuizRequest(
        @NotBlank
        String title,
        @NotBlank
        String text,
        @NotNull
        @Size(min = 2)
        List<String> options,
        @JsonProperty("answer")
        List<Integer> answerIndices
) {
}
