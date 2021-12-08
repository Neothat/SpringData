package ru.geekbrains.springdata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.springdata.entities.Product;
import ru.geekbrains.springdata.exceptions.ProductNotFoundException;
import ru.geekbrains.springdata.repositories.ProductRepository;
import ru.geekbrains.springdata.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Transactional
    @Override
    public List<Product> getAllProducts(Integer min, Integer max) {
        return productRepository.findAllByCostBetweenOrderByCostAsc(min, max);
    }

    @Transactional
    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public void addProduct() {
//        productRepository.save();
    }

    @Transactional
    @Override
    public void changeScore(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Unable to change product's score. Product not found, id: " + productId));
        product.setScore(product.getScore() + delta);
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
