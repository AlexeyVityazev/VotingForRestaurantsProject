package ru.vityazev.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vityazev.voting.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query("SELECT e FROM Dish e WHERE e.restaurant.name=:name")
    List<Dish> findRestaurantDishes(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish e WHERE e.name= ?1 AND e.restaurant.id = ?2")
    void deleteByDishAndRestaurantId(String dishName, int id);
}
