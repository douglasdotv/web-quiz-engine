package br.com.dv.engine.repository;

import br.com.dv.engine.entity.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String email);

}
