package ru.vityazev.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vityazev.voting.model.MenuItem;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MenuRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    @Modifying
    @Query("SELECT e FROM MenuItem e WHERE e.date=:date")
    List<MenuItem> findMenusByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("SELECT e FROM MenuItem e WHERE e.date= ?1 AND e.restaurant.id = ?2")
    List<MenuItem> getMenuByDateAndId(LocalDate date, int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem e WHERE e.restaurant.id= ?1 AND e.dish.id = ?2 AND e.date = ?3")
    void deleteByRestaurantIdAndDishIdAndDate(int restaurantId, int dishId, LocalDate localDate);
}

