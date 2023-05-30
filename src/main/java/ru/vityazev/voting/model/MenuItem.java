package ru.vityazev.voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menu")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC) //TODO return PROTECTED
public class MenuItem extends BaseEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    public MenuItem(@NotNull LocalDate date, Restaurant restaurant, Dish dish) {
        this.date = date;
        this.restaurant = restaurant;
        this.dish = dish;
    }

    //    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "MENU_DISH",
//            joinColumns = @JoinColumn(name = "menu_id"),
//            inverseJoinColumns = @JoinColumn(name = "dish_id"))
//
//   private List<Dish> dishes;

}
