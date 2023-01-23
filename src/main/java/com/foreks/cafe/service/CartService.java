package com.foreks.cafe.service;

import com.foreks.cafe.domain.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    // Save operation
    Cart saveCart(Cart cart);

    // Read operation
    List<Cart> listCarts();

    Optional<Cart> listCart(Long id);

    // Delete operation
    void deleteCartById(Long cartId);

}
