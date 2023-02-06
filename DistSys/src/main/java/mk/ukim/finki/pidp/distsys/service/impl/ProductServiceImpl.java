package mk.ukim.finki.pidp.distsys.service.impl;

import mk.ukim.finki.pidp.distsys.model.Product;
import mk.ukim.finki.pidp.distsys.repository.ProductRepository;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public String save(Product product) {
        productRepository.save(product);
        return "Product "+ product.getName() + " was saved!";
    }

    @Override
    public String edit(long id, Product newProduct) {
        Product found = productRepository.findById(id).get();
        found.setName(newProduct.getName());
        found.setDescription(newProduct.getDescription());
        found.setPrice(newProduct.getPrice());
        return save(found);
    }

    @Override
    public void delete(long id) {
        productRepository.delete(findById(id).get());
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public String deleteById(Long id) {
        String name = productRepository.findById(id).get().getName();
        productRepository.deleteById(id);
        return "Product " + name + " was deleted!";
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }
}