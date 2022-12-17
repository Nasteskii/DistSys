package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.User;

public interface AuthService {
    User login(String username, String password);
}
