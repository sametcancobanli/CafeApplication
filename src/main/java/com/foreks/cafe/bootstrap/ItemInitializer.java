package com.foreks.cafe.bootstrap;

import com.foreks.cafe.configuration.ItemConfiguration;
import com.foreks.cafe.temp_item.BaseItem;
import com.foreks.cafe.temp_item.BaseItemRepository;
import com.foreks.cafe.temp_supplement.BaseSupplementRepository;
import org.springframework.stereotype.Component;


@Component
public class ItemInitializer {

    private BaseItemRepository baseItemRepository;

    private BaseSupplementRepository baseSupplementRepository;

    private ItemConfiguration itemConfiguration;


    public ItemInitializer(BaseItemRepository baseItemRepository, BaseSupplementRepository baseSupplementRepository, ItemConfiguration itemConfiguration) {
        this.baseItemRepository = baseItemRepository;
        this.baseSupplementRepository = baseSupplementRepository;
        this.itemConfiguration = itemConfiguration;
    }

    public void initializeItem(){
        baseItemRepository.save(new BaseItem(1L,itemConfiguration.getCoffeeName(),itemConfiguration.getCoffeePrice()));
        baseItemRepository.save(new BaseItem(2L,itemConfiguration.getLatteName(),itemConfiguration.getLattePrice()));
        baseItemRepository.save(new BaseItem(3L,itemConfiguration.getMochaName(),itemConfiguration.getMochaPrice()));
        baseItemRepository.save(new BaseItem(4L,itemConfiguration.getTeaName(),itemConfiguration.getTeaPrice()));

    }

}
