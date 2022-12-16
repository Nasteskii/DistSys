package mk.ukim.finki.pidp.distsys.repository;

import mk.ukim.finki.pidp.distsys.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
