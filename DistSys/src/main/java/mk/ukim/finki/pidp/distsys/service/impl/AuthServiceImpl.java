package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.pidp.distsys.repository.UserRepository;
import mk.ukim.finki.pidp.distsys.service.AuthService;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUserCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}

