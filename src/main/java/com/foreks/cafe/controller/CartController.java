package com.foreks.cafe.controller;

import com.foreks.cafe.domain.Cart;
import com.foreks.cafe.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cart")
public class CartController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Save operation
    @PostMapping(value = "/create",headers = "Accept=application/json")
    ResponseEntity <Cart> createCart(@RequestBody Map<String, String> payload)
    {
        log.debug("Entering create cart endpoint");
        try {
            log.info("Cart with name " + payload.get("name") + " was created.");
            Cart cart = new Cart(payload.get("name"));
            cartService.saveCart(cart);
            return new ResponseEntity(cart, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Unable to create cart with name: " + payload.get("name") + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //All List operation
    @GetMapping( "/list")
    ResponseEntity <List<Cart>> listCarts()
    {
        log.debug("Entering list carts endpoint");
        try {
            log.info("Carts listed.");
            List<Cart> carts = cartService.listCarts();
            return new ResponseEntity(carts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list carts, message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Single List operation
    @GetMapping( "/list/{id}")
    ResponseEntity <Optional<Cart>> listCart(@PathVariable("id") Long id)
    {
        log.debug("Entering list cart with ID " + id + " endpoint");
        try {
            log.info("Cart with ID " + id + " listed.");
            Cart cart = cartService.listCart(id).get();
            cart.discountedPrice();
            return new ResponseEntity(cart, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to list cart with ID " + id + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete operation
    @DeleteMapping("/delete/{id}")
    ResponseEntity <String> deleteCartById(@PathVariable("id") Long cartId)
    {

        log.debug("Entering delete cart with ID " + cartId + " endpoint");
        try {
            log.info("Cart with ID " + cartId + " was deleted");
            cartService.deleteCartById(cartId);
            return new ResponseEntity("Cart with ID " + cartId + " was deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unable to delete cart with ID " + cartId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
