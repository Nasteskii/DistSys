package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.Client;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.pidp.distsys.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.pidp.distsys.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.pidp.distsys.repository.ClientRepository;
import mk.ukim.finki.pidp.distsys.service.ClientService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;


@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
//    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public String clientUsername(String username) {
        return username;
    }

    public String clientGender(String username) {
        return clientRepository.findByUsername(username).get().getGender();
    }

    public Integer clientAge(String username) {
        return clientRepository.findByUsername(username).get().getAge();
    }

    @Override
    public Client loadUserByUsername(String s) throws UsernameNotFoundException {
        return clientRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
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
