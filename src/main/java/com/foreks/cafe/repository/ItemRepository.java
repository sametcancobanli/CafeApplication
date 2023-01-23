package com.foreks.cafe.repository;

import com.foreks.cafe.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
}
