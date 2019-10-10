package com.beehyv.shoppingcart.controller;

import java.util.List;

import com.beehyv.shoppingcart.dto.ProductDTO;
import com.beehyv.shoppingcart.entity.Product;
import com.beehyv.shoppingcart.mapper.ProductMapper;
import com.beehyv.shoppingcart.repo.ProductRepo;
import com.beehyv.shoppingcart.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/products/add-product")
    public ProductDTO addProduct(ProductDTO productDTO) {

        return ProductMapper.INSTANCE.toProductDTO(productServices.addProduct(ProductMapper.INSTANCE.toProduct(productDTO)));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/products/update")
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return ProductMapper.INSTANCE.toProductDTO(productServices.updateProduct(ProductMapper.INSTANCE.toProduct(productDTO)));
    }

    @GetMapping("/products/getById/{productId}")
    public ProductDTO getProduct(@PathVariable("productId") long productId) {
        return ProductMapper.INSTANCE.toProductDTO(productServices.getProduct(productId));
    }

    @GetMapping("/products/{category}")
    public List<ProductDTO> getProductByCategory(@PathVariable("category") String category) {
        return ProductMapper.INSTANCE.toProductDTOS(productServices.getProductByCategory(category));
    }
}
