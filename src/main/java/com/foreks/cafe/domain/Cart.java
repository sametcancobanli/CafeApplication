package com.foreks.cafe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Carts")
@Builder
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    private String name;

    private Double totalCost;

    private Double discountedCost;

    private Integer totalItem;

    public Cart(String name) {
        this.setName(name);
        this.setTotalCost(0.0);
        this.setDiscountedCost(0.0);
        this.setTotalItem(0);
    }

    public void discountedPrice(){

        setDiscountedCost(getTotalCost() - discount());

    }

    private Double discount() {

        if(totalCost >= 36){

            var priceOne = totalCost * (0.25);
            var priceTwo = 0.0;

            if(totalItem >= 3){
                for (Item item : items) {
                    priceTwo = Math.min(priceOne, item.getTotalCost());
                }
                return Math.max(priceOne, priceTwo);
            }

            return priceOne;

        }else if (totalCost < 36 && totalItem >= 3) {

            var minItem = Double.MAX_VALUE;
            for (Item item : items) {
                minItem = Math.min(minItem, item.getTotalCost());
            }
            return minItem;
        }

        return 0.0;
    }
}
