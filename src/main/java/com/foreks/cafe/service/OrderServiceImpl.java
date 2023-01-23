package com.foreks.cafe.service;

import com.foreks.cafe.domain.Order;
import com.foreks.cafe.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order){

        return orderRepository.save(order);
    }

}
