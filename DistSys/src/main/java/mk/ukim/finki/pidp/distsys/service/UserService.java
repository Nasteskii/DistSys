package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Role;
import mk.ukim.finki.pidp.distsys.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    String username(String username);

    String userGender(String username);

    Integer userAge(String username);

    List<User> findAll();

    User loadUserByUsername(String username);

    User register(String username, String password, String repeatPassword,
                  String name, String surname, String gender, Integer age, Role role);

    void deleteAll();
}
