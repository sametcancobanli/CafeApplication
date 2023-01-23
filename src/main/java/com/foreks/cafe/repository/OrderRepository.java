package com.foreks.cafe.repository;

import com.foreks.cafe.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
