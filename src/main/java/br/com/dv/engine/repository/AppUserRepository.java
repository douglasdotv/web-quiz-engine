package br.com.dv.engine.repository;

import br.com.dv.engine.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
}
