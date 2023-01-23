package com.foreks.cafe.bootstrap;

import com.foreks.cafe.configuration.SupplementConfiguration;
import com.foreks.cafe.temp_item.BaseItemRepository;
import com.foreks.cafe.temp_supplement.BaseSupplement;
import com.foreks.cafe.temp_supplement.BaseSupplementRepository;
import org.springframework.stereotype.Component;

@Component
public class SupplementInitializer {

    private BaseItemRepository baseItemRepository;

    private BaseSupplementRepository baseSupplementRepository;

    private SupplementConfiguration supplementConfiguration;

    public SupplementInitializer(BaseItemRepository baseItemRepository, BaseSupplementRepository baseSupplementRepository, SupplementConfiguration supplementConfiguration) {
        this.baseItemRepository = baseItemRepository;
        this.baseSupplementRepository = baseSupplementRepository;
        this.supplementConfiguration = supplementConfiguration;
    }

    public void initializeSupplement(){

        baseSupplementRepository.save(new BaseSupplement(1L,supplementConfiguration.getMilkName(),supplementConfiguration.getMilkPrice()));
        baseSupplementRepository.save(new BaseSupplement(2L,supplementConfiguration.getHazelnutName(), supplementConfiguration.getHazelnutPrice()));
        baseSupplementRepository.save(new BaseSupplement(3L,supplementConfiguration.getChocolateName(), supplementConfiguration.getChocolatePrice()));
        baseSupplementRepository.save(new BaseSupplement(4L,supplementConfiguration.getLemonName(), supplementConfiguration.getLemonPrice()));
    }
}
