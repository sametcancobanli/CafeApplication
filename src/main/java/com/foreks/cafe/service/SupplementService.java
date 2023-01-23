package com.foreks.cafe.service;

import com.foreks.cafe.domain.Supplement;

import java.util.List;
import java.util.Optional;

public interface SupplementService {

    // Save operation
    Supplement saveSupplement(Long supplementId, int amount);

    // Read operation
    List<Supplement> fetchSupplementList();

    Optional<Supplement> fetchSupplement(Long id);

    // Delete operation
    void deleteSupplementById(Long supplementId);
}
