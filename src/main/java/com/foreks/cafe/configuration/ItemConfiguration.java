package com.foreks.cafe.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("beverage")
@Getter
@Setter
public class ItemConfiguration {

    @Value("${beverage.name.coffee}")
    private String coffeeName;

    @Value("${beverage.name.tea}")
    private String teaName;

    @Value("${beverage.name.latte}")
    private String latteName;

    @Value("${beverage.name.mocha}")
    private String mochaName;

    @Value("${beverage.price.coffee}")
    private Double coffeePrice;

    @Value("${beverage.price.tea}")
    private Double teaPrice;

    @Value("${beverage.price.latte}")
    private Double lattePrice;

    @Value("${beverage.price.mocha}")
    private Double mochaPrice;
}
