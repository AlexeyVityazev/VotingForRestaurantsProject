package ru.vityazev.voting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.vityazev.voting.util.validation.NoHtml;

@Entity
@Table(name = "dish", uniqueConstraints =
@UniqueConstraint(columnNames = {"rest_id", "name"}))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Dish extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    private int price;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(name = "name", nullable = false)
    @NoHtml
    protected String name;

    public Dish(Restaurant restaurant, int price, String name) {
        this.restaurant = restaurant;
        this.price = price;
        this.name = name;
    }
}
