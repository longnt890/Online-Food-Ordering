package com.project1.Online.Food.Ordering.service;

import com.project1.Online.Food.Ordering.model.Cart;
import com.project1.Online.Food.Ordering.model.CartItem;
import com.project1.Online.Food.Ordering.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long cartItemId) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

}
