package com.beehyv.shoppingcart.repo;

import com.beehyv.shoppingcart.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    SubCategory findByName(String name);

    List<SubCategory> findByNameContaining(String search);
}
