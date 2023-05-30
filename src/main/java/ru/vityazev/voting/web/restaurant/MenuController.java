package ru.vityazev.voting.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vityazev.voting.model.Dish;
import ru.vityazev.voting.model.MenuItem;
import ru.vityazev.voting.model.Restaurant;
import ru.vityazev.voting.repository.DishRepository;
import ru.vityazev.voting.repository.MenuRepository;
import ru.vityazev.voting.repository.RestaurantRepository;
import ru.vityazev.voting.to.DishTo;
import ru.vityazev.voting.to.MenuTo;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    static final String REST_URL = "/api/admin/menu";
    @Autowired
    MenuRepository repository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    DishRepository dishRepository;

    @GetMapping
    public List<MenuItem> getMenuByDateAndRestaurant(@RequestParam int restaurantId, LocalDate localDate){
        log.info("get Menu Item by restaurantId and date ");
        return repository.getMenuByDateAndId(localDate, restaurantId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Valid @RequestBody MenuTo menuTo) {
        log.info("delete Menu Item dishId={}, RestaurantId={}",menuTo.getIdDish(),menuTo.getIdRestaurant());
        repository.deleteByRestaurantIdAndDishIdAndDate(menuTo.getIdRestaurant(), menuTo.getIdDish(), menuTo.getDate());
    }

    @PostMapping
    public void saveItem(@Valid @RequestBody MenuTo menuTo) {
        log.info("save menuItem {} ", menuTo);
        Optional<Restaurant> optional = restaurantRepository.findById(menuTo.getIdRestaurant());
        Restaurant restaurant = optional.get();

        Optional<Dish> optionalDish = dishRepository.findById(menuTo.getIdDish());
        Dish dish = optionalDish.get();
        LocalDate localDate = menuTo.getDate();

        MenuItem menuItem1 = new MenuItem(localDate,restaurant,dish);
        repository.save(menuItem1);
    }

}
