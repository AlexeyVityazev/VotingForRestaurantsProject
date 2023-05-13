package ru.javaops.topjava2.web.restaurant;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.DishRestaurant;
import ru.javaops.topjava2.repository.DishRestaurantRepository;

import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@Slf4j
@RestController
@RequestMapping(value = DishRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestaurantController {
    static final String REST_URL = "/api/dish";
    @Autowired
    private DishRestaurantRepository repository;

    @GetMapping
    public List<DishRestaurant> getAll() {
        log.info("getAll dishes");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @GetMapping("/{id}")
    public DishRestaurant get(@PathVariable int id) {
        Optional<DishRestaurant> optional = repository.findById(id);
        DishRestaurant dishRestaurant = optional.get();
        return dishRestaurant;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody DishRestaurant dishRestaurant, @PathVariable int id) {
        log.info("update {} with id={}", dishRestaurant, id);
        assureIdConsistent(dishRestaurant, id);
        repository.save(dishRestaurant);
    }

    @PostMapping
    public void saveDish(DishRestaurant dishRestaurant) {
        repository.save(dishRestaurant);
    }
}
