package ru.vityazev.voting.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "dish_restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DishRestaurant extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    @Column(name = "price")
    private int price;
}
