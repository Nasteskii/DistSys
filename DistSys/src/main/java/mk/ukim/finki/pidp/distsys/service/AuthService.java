package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Client;

public interface AuthService {
    Client login(String username, String password);
}
