package br.com.dv.engine.dto;

import java.util.List;

public record QuizResponse(Integer id, String title, String text, List<String> options) {
}
