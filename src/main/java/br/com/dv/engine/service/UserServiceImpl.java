package br.com.dv.engine.service;

import br.com.dv.engine.dto.UserRegistrationRequest;
import br.com.dv.engine.entity.AppUser;
import br.com.dv.engine.exception.EmailAlreadyTakenException;
import br.com.dv.engine.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void registerUser(UserRegistrationRequest userRegistration) {
        String email = userRegistration.email();

        if (isEmailTaken(email)) {
            throw new EmailAlreadyTakenException(email);
        }

        String password = userRegistration.password();
        String encodedPassword = passwordEncoder.encode(password);

        AppUser user = new AppUser();

        user.setEmail(email);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}
