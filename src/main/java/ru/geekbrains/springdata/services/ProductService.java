package ru.geekbrains.springdata.services;

import ru.geekbrains.springdata.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    void addProduct();

    void deleteProductById(Long id);
}
