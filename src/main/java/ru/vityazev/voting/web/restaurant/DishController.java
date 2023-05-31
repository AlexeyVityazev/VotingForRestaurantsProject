package ru.vityazev.voting.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vityazev.voting.model.Dish;
import ru.vityazev.voting.model.Restaurant;
import ru.vityazev.voting.repository.DishRepository;
import ru.vityazev.voting.repository.RestaurantRepository;
import ru.vityazev.voting.to.DishTo;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/api/admin/dishes";
    @Autowired
    private DishRepository repository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/by-restaurant-name")
    public ResponseEntity<List<Dish>> getAllRestaurantDishes(@RequestParam String restaurantName) {
        log.info("get Restaurant dishes");
        return ResponseEntity.ok(repository.findRestaurantDishes(restaurantName));
    }

    @GetMapping("/{dishId}")
    public ResponseEntity<Dish> get(@PathVariable int dishId) {
        log.info("get dish with id={}", dishId);
        return ResponseEntity.of(repository.findById(dishId));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody DishTo dishTo) {
        log.info("delete dish name={}, Restaurant name={}", dishTo.getDishName(), dishTo.getRestaurantName());
        Optional<Restaurant> optional = restaurantRepository.findByName(dishTo.getRestaurantName());
        if(optional.isPresent()) {
            Restaurant restaurant = optional.get();
            repository.deleteByDishAndRestaurantId(dishTo.getDishName(), restaurant.id());
        }
    }

    @PostMapping
    public void saveDish(@Valid @RequestBody DishTo dishTo) {
        log.info("save dish {} ", dishTo);
        Optional<Restaurant> optional = restaurantRepository.findByName(dishTo.getRestaurantName());
        if(optional.isPresent()) {
            Restaurant restaurant = optional.get();
            Dish dish = new Dish(restaurant, dishTo.getDishPrice(), dishTo.getDishName());
            repository.save(dish);
        }
    }
}
