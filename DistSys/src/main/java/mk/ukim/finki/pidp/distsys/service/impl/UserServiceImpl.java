package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.repository.UserRepository;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String username(String username) {
        return username;
    }

    public String userGender(String username) {
        return userRepository.findByUsername(username).get().getGender();
    }

    public Integer userAge(String username) {
        return userRepository.findByUsername(username).get().getAge();
    }

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

//    public Client register(String username, String password, String repeatPassword, String gender, int age, Role userRole) {
//        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
//            throw new InvalidUsernameOrPasswordException();
//        if (!password.equals(repeatPassword))
//            throw new PasswordsDoNotMatchException();
//        if(this.clientRepository.findByUsername(username).isPresent())
//            throw new UsernameAlreadyExistsException(username);
//        Client client = new Client(username,passwordEncoder.encode(password),gender, age, userRole);
//        return clientRepository.save(client);
//    }

}
