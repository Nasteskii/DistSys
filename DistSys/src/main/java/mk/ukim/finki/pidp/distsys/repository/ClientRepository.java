package mk.ukim.finki.pidp.distsys.repository;

import mk.ukim.finki.pidp.distsys.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsernameAndPassword(String username, String password);

    Optional<Client> findByUsername(String username);
}
