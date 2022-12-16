package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Client;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientService {
    String clientUsername(String username);
    String clientGender(String username);
    Integer clientAge(String username);

    Client loadUserByUsername(String username);
}
