package br.com.dv.engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AnswerSubmissionRequest(@JsonProperty("answer") List<Integer> answerIndices) {
}
