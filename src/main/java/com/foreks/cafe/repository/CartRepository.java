package com.foreks.cafe.repository;

import com.foreks.cafe.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
}
