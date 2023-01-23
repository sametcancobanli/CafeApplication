package com.foreks.cafe.repository;

import com.foreks.cafe.domain.BaseItem;
import org.springframework.data.repository.CrudRepository;

public interface BaseItemRepository extends CrudRepository<BaseItem,Long> {
}
