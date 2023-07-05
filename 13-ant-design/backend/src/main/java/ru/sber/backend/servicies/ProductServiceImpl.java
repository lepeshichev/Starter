package ru.sber.backend.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.backend.entity.Product;
import ru.sber.backend.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long save(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    @Override
    public List<Product> findAll(String name) {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteById(long id) {
        productRepository.deleteById(id);
        return true;
    }
}
