package com.foreks.cafe.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Supplements")
@Builder
public class Supplement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplementId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    private String name;
    private Integer amount;
    private Double cost;
    private Double totalCost;

    public Supplement(Long supplementId,String name, Double cost, Integer amount) {
        this.supplementId = supplementId;
        this.name = name;
        this.cost = cost;
        this.amount = amount;
        this.totalCost = amount * cost;
    }

}
