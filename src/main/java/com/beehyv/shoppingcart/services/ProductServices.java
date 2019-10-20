package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.dto.Filters;
import com.beehyv.shoppingcart.entity.Category;
import com.beehyv.shoppingcart.entity.Product;
import com.beehyv.shoppingcart.entity.SubCategory;
import com.beehyv.shoppingcart.repo.CategoryRepo;
import com.beehyv.shoppingcart.repo.ProductRepo;
import com.beehyv.shoppingcart.repo.SubCategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;

import javax.persistence.Query;
import javax.persistence.criteria.*;

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
    @Autowired
    EntityManager entityManager;

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
    public List<SubCategory> getSubCategories(String category)
    {
        List<SubCategory> subCategories=new ArrayList<>();
        try{
          subCategories=categoryRepo.findByName(category).getSubCategories();
        }
        catch (Exception e){
            return null;
        }
        return subCategories;
    }
    public ResponseEntity addSubCategoryToCategory(String category,SubCategory subCategory){
        Category category1=categoryRepo.findByName(category);
        List<SubCategory> subCategories=new ArrayList<>();
        try{
            if(category1.getSubCategories()!=null)
                subCategories=category1.getSubCategories();
            System.out.println("outside"+ subCategories);
            for(int i=0;i<subCategories.size();i++)
            {
                System.out.println(subCategories.get(i).getName()+subCategory.getName());
                if(subCategories.get(i).getName()==subCategory.getName())
                    return ResponseEntity.ok("Success");
            }
        }
        catch (Exception e)
        {

        }
        subCategory=subCategoryRepo.findByName(subCategory.getName());
        subCategories.add(subCategory);
        System.out.println(subCategories);
        category1.setSubCategories(subCategories);
        categoryRepo.save(category1);
        return ResponseEntity.ok("Success");
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
        Category category1=categoryRepo.findByName(category);
        CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery=criteriaBuilder.createQuery(Product.class);
        Root<Product> root= criteriaQuery.from(Product.class);
        Join<Product,SubCategory> subCategory=root.join("subCategories");
        //Join<Product,Category> categoryJoin=root.join("category");
        Predicate[] predicates = new Predicate[3];
        predicates[0] = criteriaBuilder.between(root.get("price"),filters.getMinPrice(),filters.getMaxPrice());
        predicates[1]= criteriaBuilder.equal(root.get("category"),category1);
        // predicates[1]= criteriaBuilder.equal(categoryJoin.get("name"),category);
        predicates[2] = subCategory.get("name").in(filters.getSubCategories());
        criteriaQuery.select(root).where(predicates);
        List<Product> products= entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println(products);
        return products;
    }

}
