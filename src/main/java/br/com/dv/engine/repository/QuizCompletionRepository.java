package br.com.dv.engine.repository;

import br.com.dv.engine.entity.AppUser;
import br.com.dv.engine.entity.QuizCompletion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuizCompletionRepository extends CrudRepository<QuizCompletion, Integer> {

    Page<QuizCompletion> findAllByUserOrderByCompletedAtDesc(AppUser user, Pageable pageable);

}
