package com.foreks.cafe.service;

import com.foreks.cafe.domain.Supplement;
import com.foreks.cafe.repository.SupplementRepository;
import com.foreks.cafe.temp_supplement.BaseSupplement;
import com.foreks.cafe.temp_supplement.BaseSupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplementServiceImpl implements SupplementService {

    @Autowired
    private SupplementRepository supplementRepository;

    @Autowired
    private BaseSupplementRepository baseSupplementRepository;



    // Save operation
    public Supplement saveSupplement(Long supplementId, int amount){
        BaseSupplement baseSupplement = baseSupplementRepository.findById(supplementId).get();
        Supplement supplement = new Supplement(baseSupplement.getId(), baseSupplement.getName(), baseSupplement.getCost(), amount);
        return supplementRepository.save(supplement);
    }

    // Read operation
    @Override
    public List<Supplement> fetchSupplementList()
    {
        return (List<Supplement>) supplementRepository.findAll();
    }

    @Override
    public Optional<Supplement> fetchSupplement(Long id)
    {
        return (Optional<Supplement>) supplementRepository.findById(id);
    }

    // Delete operation
    @Override
    public void deleteSupplementById(Long supplementId)
    {
        supplementRepository.deleteById(supplementId);
    }
}
