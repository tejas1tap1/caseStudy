package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.Category;
import com.beehyv.shoppingcart.entity.Product;
import com.beehyv.shoppingcart.entity.SubCategory;
import com.beehyv.shoppingcart.repo.CategoryRepo;
import com.beehyv.shoppingcart.repo.ProductRepo;
import com.beehyv.shoppingcart.repo.SubCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    public Product addProduct(Product product) {
        Category category = categoryRepo.findByName(product.getCategory().getName());
        if(category!=null)
        {
         product.setCategory(category);
        }
        SubCategory subCategory = subCategoryRepo.findByName(product.getSubCategory().getName());
        if(subCategory!=null)
        {
            product.setSubCategory(subCategory);
        }

        return productRepo.save(product);
    }
    //need to be rewrite the code
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public Product getProduct(long productId) {
        return productRepo.findByProductId(productId);
    }

    public List<Product> getProductByCategory(String category) {

        return productRepo.findByCategory(categoryRepo.findByName(category));
    }
}
