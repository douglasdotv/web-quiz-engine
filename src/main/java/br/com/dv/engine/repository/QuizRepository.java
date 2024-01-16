package br.com.dv.engine.repository;

import br.com.dv.engine.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
