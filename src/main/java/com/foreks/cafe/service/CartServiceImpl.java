package com.foreks.cafe.service;

import com.foreks.cafe.domain.Cart;
import com.foreks.cafe.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Save operation
    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    // Read operation
    @Override
    public List<Cart> listCarts()
    {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public Optional<Cart> listCart(Long id)
    {
        return (Optional<Cart>) cartRepository.findById(id);
    }

    // Delete operation
    @Override
    public void deleteCartById(Long orderId)
    {
        cartRepository.deleteById(orderId);
    }

}
