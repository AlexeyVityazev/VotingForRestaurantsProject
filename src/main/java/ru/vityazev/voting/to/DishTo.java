package ru.vityazev.voting.to;

import lombok.Data;
import ru.vityazev.voting.model.Restaurant;
import ru.vityazev.voting.repository.RestaurantRepository;

import java.util.Collection;

@Data
public class DishTo {

    private String restaurantName;
    private String dishName;
    private int dishPrice;

}
