package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.Cart;
import com.beehyv.shoppingcart.entity.CartItem;

import com.beehyv.shoppingcart.entity.Product;
import com.beehyv.shoppingcart.repo.CartItemRepo;
import com.beehyv.shoppingcart.repo.CartRepo;
import com.beehyv.shoppingcart.repo.ProductRepo;
import com.beehyv.shoppingcart.repo.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CartServices {
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserProfileRepo userProfileRepo;
    @Autowired
    CartItemRepo cartItemRepo;
    @Autowired
    ProductRepo productRepo;

    public List<CartItem> getAllCartItems(long userId) {

        return cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId)).getCartItems();

    }

    public CartItem getCartItem(long userId, long cartItemId) {
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        return cartItemRepo.findByCartItemIdAndCart(cartItemId, cart);
    }

    public CartItem addToCart(long userId, long productId) {

        Product product = productRepo.findByProductId(productId);
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        CartItem cartItem=new CartItem();
        if (cartItemRepo.findByProductAndCart(product, cart) == null) {
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cartItemRepo.save(cartItem);

        } else {
            cartItem = cartItemRepo.findByProductAndCart(product, cart);
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepo.save(cartItem);
        }
        return cartItem;
    }
    public String removeFromCart(long userId,long productId)
    {
        Product product = productRepo.findByProductId(productId);
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        CartItem cartItem = cartItemRepo.findByProductAndCart(product, cart);

        if (cartItem == null) {
           return "product with the specified productId is not in the cart";

        } else {
            cartItemRepo.delete(cartItem);
            return cartItem.getProduct().getName()+" removed from cart";
        }
    }
    public CartItem changeQuantityOfProduct(long userId,long productId,long quantity)
    {
        Product product = productRepo.findByProductId(productId);
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        CartItem cartItem = cartItemRepo.findByProductAndCart(product, cart);
        cartItem.setQuantity(quantity);
        return cartItemRepo.save(cartItem);

    }

}
