package ru.geekbrains.springdata.services;

import org.springframework.data.domain.Page;
import ru.geekbrains.springdata.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts(Integer min, Integer max);

    Optional<Product> getProductById(Long id);

    void addProduct();

    void saveProduct(Product product);

    void deleteProductById(Long id);

    void changeScore(Long productId, Integer delta);

    Page<Product> find(Integer minCost, Integer maxCost, String title, Integer page);
}