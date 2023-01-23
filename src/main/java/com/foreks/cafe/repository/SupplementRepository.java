package com.foreks.cafe.repository;

import com.foreks.cafe.domain.Supplement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplementRepository extends CrudRepository<Supplement,Long> {

    Optional<Supplement> findByItemIdAndSupplementId(Long itemId, Long supplementId);
}
