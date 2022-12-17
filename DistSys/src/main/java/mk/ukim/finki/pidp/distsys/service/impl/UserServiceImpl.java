package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.Role;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.pidp.distsys.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.pidp.distsys.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.pidp.distsys.repository.UserRepository;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String username(String username) {
        return username;
    }

    public String firstName(String username) {
        return userRepository.findByUsername(username).get().getFirstName();
    }

    public String lastName(String username) {
        return userRepository.findByUsername(username).get().getLastName();
    }

    public String userGender(String username) {
        return userRepository.findByUsername(username).get().getGender();
    }

    public Integer userAge(String username) {
        return userRepository.findByUsername(username).get().getAge();
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword,
                         String name, String surname, String gender, Integer age, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if (this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User client = new User(username, passwordEncoder.encode(password), gender, age, role);
        return userRepository.save(client);
    }
}
