package mk.ukim.finki.pidp.distsys.service;

import mk.ukim.finki.pidp.distsys.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void save(Product product);
    void edit(long id, Product newProduct);
    void delete(long id);
    Product findById(long id);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long categoryId);
    long count();

    List<Product> findAll();

    void deleteById(Long id);
}