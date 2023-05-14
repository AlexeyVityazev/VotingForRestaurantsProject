package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu_restaurant")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRestaurant extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Column(name = "date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date date = new Date();


    @OneToOne
    @JoinColumn(name = "rest_id")
    Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MENU_DISH",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<DishRestaurant> dishRestaurants;

    @Column(name = "vote", nullable = false, columnDefinition = "integer default 0")
    private int votes;

    public void increaseVote() {
        votes++;
    }

}
