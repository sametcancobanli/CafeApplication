package com.foreks.cafe.controller;

import com.foreks.cafe.domain.Cart;
import com.foreks.cafe.domain.Order;
import com.foreks.cafe.repository.CartRepository;
import com.foreks.cafe.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping(path = "/api/order")
public class OrderController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    private OrderService orderService;

    private CartRepository cartRepository;

    public OrderController(OrderService orderService, CartRepository cartRepository) {
        this.orderService = orderService;
        this.cartRepository = cartRepository;
    }

    // Complete operation
    @PostMapping("/create/{id}")
    ResponseEntity <Order> createOrder(@PathVariable("id") Long cartId)
    {
        log.debug("Entering create order endpoint");
        try {
            log.info("Order with ID " + cartId + " was created.");
            LocalDate date = LocalDate.now();
            Cart cart = cartRepository.findById(cartId).get();
            cart.discountedPrice();
            Order order = new Order(cart, date);
            orderService.saveOrder(order);
            return new ResponseEntity(order, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Unable to create order with ID: " + cartId + ", message: " + e.getMessage(), e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
