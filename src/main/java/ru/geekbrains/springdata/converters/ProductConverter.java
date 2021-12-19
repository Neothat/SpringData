package ru.geekbrains.springdata.converters;

import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.dto.ProductDtoWithSummaryCount;
import ru.geekbrains.springdata.entities.Product;

import java.util.List;
import java.util.Map;

public interface ProductConverter {
    public Product dtoToEntity(ProductDto productDto);

    public ProductDto entityToDto(Product product);

    public List<ProductDtoWithSummaryCount> mapToListProduct(Map<ProductDto, Integer> productMap);
}
