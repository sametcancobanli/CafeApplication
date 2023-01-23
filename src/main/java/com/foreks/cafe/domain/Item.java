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
@Table(name= "Items")
@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Supplement> supplements = new HashSet<>();
    private String name;
    private Double cost;

    private Double totalCost;

    public Item(String name, Double cost) {
        this.name = name;
        this.cost = cost;
    }

}
