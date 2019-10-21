package com.beehyv.shoppingcart.services;

import com.beehyv.shoppingcart.entity.Cart;
import com.beehyv.shoppingcart.entity.CartItem;
import com.beehyv.shoppingcart.entity.Product;
import com.beehyv.shoppingcart.entity.UserProfile;
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
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        if (cart == null) return null;
        else return cart.getCartItems();

    }

    public CartItem getCartItem(long userId, long cartItemId) {
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        return cartItemRepo.findByCartItemIdAndCart(cartItemId, cart);
    }

    public CartItem addToCart(long userId, long productId) {

        Product product = productRepo.findByProductId(productId);
        UserProfile userProfile = userProfileRepo.findByUserId(userId);
        Cart cart = cartRepo.findCartByUserProfile(userProfile);
        if (cart == null) {
            Cart cartNew = new Cart();
            cartNew.setUserProfile(userProfile);
            cartRepo.save(cartNew);
            cart = cartNew;
        }

        CartItem cartItem = new CartItem();
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

    public String removeFromCart(long userId, long productId) {
        Product product = productRepo.findByProductId(productId);
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        CartItem cartItem = cartItemRepo.findByProductAndCart(product, cart);

        if (cartItem == null) {
            return "Product with the specified productId is not in the cart";

        } else {
            cartItemRepo.delete(cartItem);
            if (cart.getCartItems().size() == 0) cartRepo.delete(cart);
            return cartItem.getProduct().getName() + " Removed from cart";
        }
    }

    public CartItem changeQuantityOfProduct(long userId, long productId, long quantity) {
        Product product = productRepo.findByProductId(productId);
        Cart cart = cartRepo.findCartByUserProfile(userProfileRepo.findByUserId(userId));
        CartItem cartItem = cartItemRepo.findByProductAndCart(product, cart);
        cartItem.setQuantity(quantity);
        return cartItemRepo.save(cartItem);

    }

}
