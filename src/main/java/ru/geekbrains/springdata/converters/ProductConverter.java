package ru.geekbrains.springdata.converters;

import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.entities.Product;

public interface ProductConverter {
    public Product dtoToEntity(ProductDto productDto);

    public ProductDto entityToDto(Product product);
}
