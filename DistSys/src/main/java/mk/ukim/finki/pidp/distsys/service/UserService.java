package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    String username(String username);
    String userGender(String username);
    Integer userAge(String username);

    User loadUserByUsername(String username);
}
