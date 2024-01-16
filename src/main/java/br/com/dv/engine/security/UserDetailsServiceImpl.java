package br.com.dv.engine.security;

import br.com.dv.engine.entity.AppUser;
import br.com.dv.engine.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String USER_NOT_FOUND_TEMPLATE = "User with e-mail %s not found.";

    private final AppUserRepository userRepository;

    public UserDetailsServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return new AppUserAdapter(userOptional.get());
        }

        throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_TEMPLATE, email));
    }

}
