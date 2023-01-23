package com.foreks.cafe;

import com.foreks.cafe.bootstrap.ItemInitializer;
import com.foreks.cafe.bootstrap.SupplementInitializer;
import com.foreks.cafe.temp_item.BaseItemRepository;
import com.foreks.cafe.temp_supplement.BaseSupplementRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CafeApplication {

    private BaseItemRepository baseItemRepository;
    private final BaseSupplementRepository baseSupplementRepository;

    CafeApplication(BaseItemRepository baseItemRepository,
                    BaseSupplementRepository baseSupplementRepository){
        this.baseItemRepository = baseItemRepository;
        this.baseSupplementRepository = baseSupplementRepository;
    }

    public static void main(String[] args) {

        ApplicationContext appCtx = SpringApplication.run(CafeApplication.class, args);
        ItemInitializer itemInitializer = appCtx.getBean(ItemInitializer.class);
        itemInitializer.initializeItem();
        SupplementInitializer supplementInitializer = appCtx.getBean(SupplementInitializer.class);
        supplementInitializer.initializeSupplement();
    }
}
