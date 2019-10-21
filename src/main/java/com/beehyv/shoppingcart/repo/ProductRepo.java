package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Category;
import com.beehyv.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProductId(long productId);

    List<Product> findByCategory(Category category);

    List<Product> findByNameContaining(String search);

    List<Product> findByDetailsContaining(String search);
}
