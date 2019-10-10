package com.beehyv.shoppingcart.repo;

import java.util.List;

import com.beehyv.shoppingcart.entity.Category;
import com.beehyv.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	
	Product findByProductId(long productId);
	List<Product> findByCategory(Category category);
}
