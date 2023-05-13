package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.DishRestaurant;
@Transactional(readOnly = true)
public interface DishRestaurantRepository extends JpaRepository<DishRestaurant, Integer> {

}
