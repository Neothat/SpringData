package ru.geekbrains.springdata.services;

import ru.geekbrains.springdata.dto.ProductDtoWithSummaryCount;

import java.util.HashSet;
import java.util.List;

public interface CartServices {
    public void addToCart(Long id);

    public void removeFromCart(Long id);

    public List<ProductDtoWithSummaryCount> showCart();
}
