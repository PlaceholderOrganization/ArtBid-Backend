package firegruppen.security.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import firegruppen.security.entity.UserWithRoles;
import firegruppen.security.repository.UserWithRolesRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    public static final String WRONG_USERNAME_OR_PASSWORD = "Incorrect username or password";

    UserWithRolesRepository userWithRolesRepository;

    public UserDetailsServiceImp(UserWithRolesRepository userWithRolesRepository) {
        this.userWithRolesRepository = userWithRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<UserWithRoles> optionalUser = userWithRolesRepository.findById(username);
        return optionalUser.orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,WRONG_USERNAME_OR_PASSWORD));
    }
}
