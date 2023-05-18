package ru.vityazev.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vityazev.voting.model.DishRestaurant;
@Transactional(readOnly = true)
public interface DishRestaurantRepository extends JpaRepository<DishRestaurant, Integer> {

}
