package com.beehyv.shoppingcart.controller;

import com.beehyv.shoppingcart.dto.Filters;
import com.beehyv.shoppingcart.dto.ProductDTO;
import com.beehyv.shoppingcart.dto.SubCategoryDTO;
import com.beehyv.shoppingcart.mapper.ProductMapper;
import com.beehyv.shoppingcart.mapper.SubCategoryMapper;
import com.beehyv.shoppingcart.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController {
    @Autowired
    private ProductServices productServices;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/products/add-product")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        //System.out.println(productDTO);
        return ProductMapper.INSTANCE.toProductDTO(productServices.addProduct(ProductMapper.INSTANCE.toProduct(productDTO)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/products/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return ProductMapper.INSTANCE.toProductDTO(productServices.updateProduct(ProductMapper.INSTANCE.toProduct(productDTO)));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addSubCategory/{category}")
    public ResponseEntity addSubCategoryToCategory(@PathVariable("category") String category, @RequestBody SubCategoryDTO subCategoryDTO) {
        //System.out.println("here"+ subCategoryDTO.getName());
        return productServices.addSubCategoryToCategory(category, SubCategoryMapper.INSTANCE.toSubCategory(subCategoryDTO));
    }

    @GetMapping("/subCategories/{category}")
    public List<SubCategoryDTO> getSubCategories(@PathVariable("category") String category) {
        return SubCategoryMapper.INSTANCE.toSubCategoryDTOS(productServices.getSubCategories(category));
    }

    @GetMapping("/products/getById/{productId}")
    public ProductDTO getProduct(@PathVariable("productId") long productId) {
        return ProductMapper.INSTANCE.toProductDTO(productServices.getProduct(productId));
    }

    @GetMapping("/products/{category}")
    public List<ProductDTO> getProductByCategory(@PathVariable("category") String category) {
        return ProductMapper.INSTANCE.toProductDTOS(productServices.getProductByCategory(category));
    }

    @GetMapping("/products/search/{searchString}")
    public List<ProductDTO> getProductBySearchString(@PathVariable("searchString") String search) {
        return ProductMapper.INSTANCE.toProductDTOS(productServices.getProductsBySearchString(search));
    }

    @PostMapping("/products/{category}/getFilteredProducts")
    public List<ProductDTO> getFilteredProductByCategory(@PathVariable("category") String category, @RequestBody Filters filters) {
        return ProductMapper.INSTANCE.toProductDTOS(productServices.getFilteredProductsByCategory(category, filters));
    }
}
