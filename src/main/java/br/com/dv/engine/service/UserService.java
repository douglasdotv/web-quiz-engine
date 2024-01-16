package br.com.dv.engine.service;

import br.com.dv.engine.dto.UserRegistrationRequest;

public interface UserService {

    void registerUser(UserRegistrationRequest userRegistration);

}
