package ru.geekbrains.springdata.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.springdata.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> scoreGreaterOrEqualsThat(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> scoreLessThanOrEqualsThat(Integer cost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> titleLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }
}
