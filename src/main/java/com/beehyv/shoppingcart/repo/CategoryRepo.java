package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByName(String name);
    Category findByNameLike(String search);
    List<Category> findByNameContaining(String search);
}
