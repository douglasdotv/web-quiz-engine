package br.com.dv.engine.repository;

import br.com.dv.engine.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

    Page<Quiz> findAll(Pageable pageable);

}
