package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.dto.Filters;
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
        System.out.println(product);
        Category category = categoryRepo.findByName(product.getCategory().getName());
        if(category!=null)
        {
         product.setCategory(category);
        }
        List<SubCategory> subCategories=new ArrayList<>();
        for(int i=0;i<product.getSubCategories().size();i++) {
            SubCategory subCategory=subCategoryRepo.findByName(product.getSubCategories().get(i).getName());
            if(subCategory!=null)
            subCategories.add(subCategory);
            else subCategories.add(product.getSubCategories().get(i));
        }
        product.setSubCategories(subCategories);

        return productRepo.save(product);
    }
    //need to be rewrite the code
    public Product updateProduct(Product product) {
        if(productRepo.findByProductId(product.getProductId())!=null)
           return productRepo.save(product);
        return null;
    }

    public Product getProduct(long productId) {
        return productRepo.findByProductId(productId);
    }

    public List<Product> getProductByCategory(String category) {

        return productRepo.findByCategory(categoryRepo.findByName(category));
    }
    public List<Product> getProductsBySearchString(String search)
    {
        List<Product> searchProduct = productRepo.findByNameContaining(search);
        searchProduct.addAll(productRepo.findByDetailsContaining(search));
//        List<SubCategory> subCategories=subCategoryRepo.findByNameContaining(search);
//        for(SubCategory subCategory: subCategories)
//        {
//            searchProduct.addAll(productRepo.findBySubCategory(subCategory));
//        }
        searchProduct.addAll(productRepo.findByCategory(categoryRepo.findByNameLike(search)));

     return  searchProduct;
    }
    public  List<Product> getFilteredProductsByCategory(String category, Filters filters)
    {
       List<Product> products=getProductByCategory(category);
       return null;
    }

}
