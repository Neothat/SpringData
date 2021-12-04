package ru.geekbrains.springdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springdata.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostAfterOrderByCostAsc(Integer cost);
    List<Product> findAllByCostAfterOrderByCostDesc(Integer cost);

    List<Product> findAllByCostBeforeOrderByCostAsc(Integer cost);
    List<Product> findAllByCostBeforeOrderByCostDesc(Integer cost);

    List<Product> findAllByCostBetweenOrderByCostAsc(Integer min, Integer max);
    List<Product> findAllByCostBetweenOrderByCostDesc(Integer min, Integer max);
}
