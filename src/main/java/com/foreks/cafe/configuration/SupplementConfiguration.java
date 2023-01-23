package com.foreks.cafe.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("supplement")
@Getter
@Setter
public class SupplementConfiguration {
    @Value("${supplement.name.milk}")
    private String milkName;

    @Value("${supplement.name.hazelnut}")
    private String hazelnutName;

    @Value("${supplement.name.chocolate}")
    private String chocolateName;

    @Value("${supplement.name.lemon}")
    private String lemonName;

    @Value("${supplement.price.milk}")
    private Double milkPrice;

    @Value("${supplement.price.hazelnut}")
    private Double hazelnutPrice;

    @Value("${supplement.price.chocolate}")
    private Double chocolatePrice;

    @Value("${supplement.price.lemon}")
    private Double lemonPrice;
}
