package ru.geekbrains.springdata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.entities.Product;
import ru.geekbrains.springdata.exceptions.ProductNotFoundException;
import ru.geekbrains.springdata.repositories.ProductRepository;
import ru.geekbrains.springdata.repositories.specifications.ProductSpecifications;
import ru.geekbrains.springdata.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public Page<Product> find(Integer minCost, Integer maxCost, String title, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterOrEqualsThat(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualsThat(maxCost));
        }
        if (title != null) {
            spec = spec.and(ProductSpecifications.titleLike(title));
        }

        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

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
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ProductNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());
        return product;
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
