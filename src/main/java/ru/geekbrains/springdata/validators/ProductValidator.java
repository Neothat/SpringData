package ru.geekbrains.springdata.validators;

import ru.geekbrains.springdata.dto.ProductDto;

public interface ProductValidator {
    public void validate(ProductDto productDto);
}
