package com.beehyv.shoppingcart.controller;


import com.beehyv.shoppingcart.mapper.CartItemMapper;
import com.beehyv.shoppingcart.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartServices cartServices;
    @Autowired
    SecurityController securityController;

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/get-cart")
    public ResponseEntity getAllCartItems(@PathVariable("userId") long userId) {
        if (userId == securityController.currentUserId()) {
            if (CartItemMapper.INSTANCE.toCartItemsDTO(cartServices.getAllCartItems(userId)) == null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("empty");
            return ResponseEntity.ok(CartItemMapper.INSTANCE.toCartItemsDTO(cartServices.getAllCartItems(userId)));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/get-cart-item/{cartItemId}")
    public ResponseEntity getCartItem(@PathVariable("userId") long userId, @PathVariable("cartItemId") long cartItemId) {
        if (userId == securityController.currentUserId()) {

            return ResponseEntity.ok(CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.getCartItem(userId, cartItemId)));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");

    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/cart/{userId}/add/{productId}")
    public ResponseEntity addToCart(@PathVariable("userId") long userId, @PathVariable("productId") long productId) {
        if (userId == securityController.currentUserId()) {

            return ResponseEntity.ok(CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.addToCart(userId, productId)));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");

    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/cart/{userId}/remove/{productId}")
    public ResponseEntity removeFromCart(@PathVariable("userId") long userId, @PathVariable("productId") long productId) {
        if (userId == securityController.currentUserId()) {

            return ResponseEntity.ok(cartServices.removeFromCart(userId, productId));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");

    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/cart/{userId}/changeQuantity/{productId}")
    public ResponseEntity changeQuantityOfProduct(@PathVariable("userId") long userId, @PathVariable("productId") long productId, @RequestBody long quantity) {
        if (userId == securityController.currentUserId()) {

            return ResponseEntity.ok(CartItemMapper.INSTANCE.cartItemToCartItemDTO(cartServices.changeQuantityOfProduct(userId, productId, quantity)));
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failure");
    }

}
