package ru.geekbrains.springdata.converters.impl;

import org.springframework.stereotype.Component;
import ru.geekbrains.springdata.converters.ProductConverter;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.dto.ProductDtoWithSummaryCount;
import ru.geekbrains.springdata.entities.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductConverterImpl implements ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }

    @Override
    public List<ProductDtoWithSummaryCount> mapToListProduct(Map<ProductDto, Integer> productMap) {
        Set<Map.Entry<ProductDto, Integer>> entrySet = productMap.entrySet();
        return entrySet
                .stream()
                .map(s -> new ProductDtoWithSummaryCount(
                        s.getKey().getId(),
                        s.getKey().getTitle(),
                        s.getKey().getCost(),
                        s.getValue()))
                .collect(Collectors.toList());
    }

}
