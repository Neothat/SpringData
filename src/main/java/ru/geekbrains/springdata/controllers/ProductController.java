package ru.geekbrains.springdata.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springdata.converters.ProductConverter;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.dto.ProductDtoWithSummaryCount;
import ru.geekbrains.springdata.entities.Product;
import ru.geekbrains.springdata.exceptions.ProductNotFoundException;
import ru.geekbrains.springdata.services.CartServices;
import ru.geekbrains.springdata.services.ProductService;
import ru.geekbrains.springdata.validators.ProductValidator;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private ProductConverter productConverter;
    private ProductValidator productValidator;
    private CartServices cartServices;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(name = "min", required = false) Integer min,
                                           @RequestParam(name = "max", required = false) Integer max,
                                           @RequestParam(name = "title", required = false) String title,
                                           @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(min, max, title, page).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.saveProduct(product);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping
    public ProductDto updateProductById(@PathVariable ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @PostMapping("/cart")
    public void addToCart(@RequestBody Long id) {
        cartServices.addToCart(id);
    }

    @GetMapping("/cart")
    public List<ProductDtoWithSummaryCount> getCart() {
        return cartServices.showCart();
    }

    @DeleteMapping("/cart/{id}")
    public void getCart(@PathVariable Long id) {
        cartServices.removeFromCart(id);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setProductValidator(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    @Autowired
    public void setCartServices(CartServices cartServices) {
        this.cartServices = cartServices;
    }
}
