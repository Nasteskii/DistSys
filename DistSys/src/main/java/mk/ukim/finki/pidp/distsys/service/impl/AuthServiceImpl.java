package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.Client;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.pidp.distsys.repository.ClientRepository;
import mk.ukim.finki.pidp.distsys.service.AuthService;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUserCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final ClientRepository clientRepository;

    public AuthServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return clientRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}

