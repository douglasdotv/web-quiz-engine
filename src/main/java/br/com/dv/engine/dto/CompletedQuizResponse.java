package br.com.dv.engine.dto;

import java.time.LocalDateTime;

public record CompletedQuizResponse(Integer id, LocalDateTime completedAt) {
}
