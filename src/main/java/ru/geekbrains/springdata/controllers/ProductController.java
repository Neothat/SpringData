package ru.geekbrains.springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springdata.entities.Product;
import ru.geekbrains.springdata.exceptions.ProductNotFoundException;
import ru.geekbrains.springdata.services.ProductService;

@RestController
public class ProductController {

    private ProductService productService;

    @GetMapping("/products")
    public Page<Product> getAllProducts(@RequestParam(name = "min", required = false) Integer min,
                                        @RequestParam(name = "max", required = false) Integer max,
                                        @RequestParam(name = "title", required = false) String title,
                                        @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1){
            page =1;
        }
        return productService.find(min, max, title, page);
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException("Product not found, id: " + id));
    }

    @GetMapping("/products/change_score")
    public void changeScore(@RequestParam Long productId, @RequestParam Integer delta) {
        productService.changeScore(productId, delta);
    }

    @PostMapping(value = "/products")
    public void addProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @GetMapping("/products/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
