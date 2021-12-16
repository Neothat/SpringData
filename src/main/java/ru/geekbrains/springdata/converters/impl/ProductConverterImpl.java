package ru.geekbrains.springdata.converters.impl;

import org.springframework.stereotype.Component;
import ru.geekbrains.springdata.converters.ProductConverter;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.entities.Product;

@Component
public class ProductConverterImpl implements ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

}
