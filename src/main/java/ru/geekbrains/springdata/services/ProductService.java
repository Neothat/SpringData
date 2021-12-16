package ru.geekbrains.springdata.services;

import org.springframework.data.domain.Page;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts(Integer min, Integer max);

    Optional<Product> getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProductById(Long id);

    Page<Product> find(Integer minCost, Integer maxCost, String title, Integer page);

    Product update(ProductDto productDto);
}