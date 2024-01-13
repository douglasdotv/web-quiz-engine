package br.com.dv.engine.dto;

import java.util.List;

public record QuizResponse(String title, String text, List<String> options) {
}
