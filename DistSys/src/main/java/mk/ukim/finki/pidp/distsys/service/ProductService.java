package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    String save(Product product);
    String edit(long id, Product newProduct);
    void delete(long id);
    Optional<Product> findById(long id);
    Optional<Product> findByName(String name);
    long count();

    List<Product> findAll();

    String deleteById(Long id);

    void deleteAll();
}