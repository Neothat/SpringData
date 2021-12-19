package ru.geekbrains.springdata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.springdata.converters.ProductConverter;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.dto.ProductDtoWithSummaryCount;
import ru.geekbrains.springdata.exceptions.ProductNotFoundException;
import ru.geekbrains.springdata.services.CartServices;
import ru.geekbrains.springdata.services.ProductService;

import java.util.HashMap;
import java.util.List;

@Service
public class CartServicesImpl implements CartServices {

    private static final Integer ONE_PRODUCT = 1;
    private final HashMap<ProductDto, Integer> productMap = new HashMap<>();

    private ProductService productService;
    private ProductConverter productConverter;

    @Override
    public void addToCart(Long id) {
        ProductDto productDto = productConverter.entityToDto(productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found, id: " + id)));

        if (productMap.containsKey(productDto)) {
            productMap.put(productDto, productMap.get(productDto) + ONE_PRODUCT);
        } else {
            productMap.put(productDto, ONE_PRODUCT);
        }
    }

    @Override
    public void removeFromCart(Long id) {
        ProductDto productDto = productConverter.entityToDto(productService.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found, id: " + id)));

        if (productMap.containsKey(productDto)) {
            if (productMap.get(productDto) >= ONE_PRODUCT)
                productMap.put(productDto, productMap.get(productDto) - ONE_PRODUCT);
        } else {
            productMap.remove(productDto);
        }
    }

    @Override
    public List<ProductDtoWithSummaryCount> showCart() {
        return productConverter.mapToListProduct(productMap);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

}
