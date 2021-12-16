package ru.geekbrains.springdata.validators.impl;

import org.springframework.stereotype.Component;
import ru.geekbrains.springdata.dto.ProductDto;
import ru.geekbrains.springdata.exceptions.ValidationException;
import ru.geekbrains.springdata.validators.ProductValidator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidatorImpl implements ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getCost() < 1) {
            errors.add("Product price cannot be less than 1");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Product cannot have an empty name");
        }
        if (errors.isEmpty()){
            throw  new ValidationException(errors);
        }
    }
}
