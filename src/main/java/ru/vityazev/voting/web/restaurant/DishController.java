package ru.vityazev.voting.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vityazev.voting.model.Dish;
import ru.vityazev.voting.model.Restaurant;
import ru.vityazev.voting.repository.DishRepository;
import ru.vityazev.voting.repository.RestaurantRepository;
import ru.vityazev.voting.to.DishTo;

import java.util.List;
import java.util.Optional;

import static ru.vityazev.voting.util.validation.ValidationUtil.assureIdConsistent;

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
    public List<Dish> getAllRestaurantDishes(@RequestParam String name) {
        log.info("get Restaurant dishes");
        return repository.findRestaurantDishes(name);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable int id) {
        log.info("get dish with id={}", id);
        Optional<Dish> optional = repository.findById(id);
        Dish dish = optional.get();
        return dish;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody DishTo dishTo) {
        log.info("delete dish name={}, Restaurant name={}", dishTo.getDishName(),dishTo.getRestaurantName());
        Optional<Restaurant> optional = restaurantRepository.findByName(dishTo.getRestaurantName());
        Restaurant restaurant = optional.get();
        repository.deleteByDishAndRestaurantId(dishTo.getDishName(), restaurant.id());
    }

//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@Valid @RequestBody Dish dish, @PathVariable int id) {
//        log.info("update {} with id={}", dish, id);
//        assureIdConsistent(dish, id);
//        repository.save(dish);
//    }

    @PostMapping
    public void saveDish(@Valid @RequestBody DishTo dishTo) {
        log.info("save dish {} ", dishTo);
        Optional<Restaurant> optional = restaurantRepository.findByName(dishTo.getRestaurantName());
        Restaurant restaurant = optional.get();
        Dish dish = new Dish(restaurant, dishTo.getDishPrice(), dishTo.getDishName());
        repository.save(dish);
    }
}
