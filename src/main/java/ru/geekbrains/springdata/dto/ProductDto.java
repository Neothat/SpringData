package ru.geekbrains.springdata.dto;

import ru.geekbrains.springdata.entities.Product;


public class ProductDto {

    private Long id;
    private String title;
    private int score;
    private int cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.score = product.getScore();
        this.cost = product.getCost();
    }

}
