package com.foreks.cafe.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Orders")
@Builder
@Component
public class Order{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart finalCart;

    private String name;

    private Double totalCost;

    private Double discountedCost;

    private Integer totalItem;
    private LocalDate createdAt;

    public Order(Cart cart, LocalDate createdAt) {
        this.setName(cart.getName());
        this.setFinalCart(cart);
        this.setTotalCost(cart.getTotalCost());
        this.setDiscountedCost(cart.getDiscountedCost());
        this.setTotalItem(cart.getTotalItem());
        this.createdAt = createdAt;
    }
}
