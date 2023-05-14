package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.MenuRestaurant;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface MenuRestaurantRepository extends JpaRepository<MenuRestaurant, Integer> {
    @Transactional
    @Modifying
    @Query("SELECT e FROM MenuRestaurant e WHERE e.date=:date")
    List<MenuRestaurant> findMenusByDate(@Param("date") Date date);

    @Transactional
    @Modifying
    @Query("SELECT e FROM MenuRestaurant e WHERE e.votes=:votes")
    List<MenuRestaurant> findMenusByVotes(@Param("votes") Integer votes);

    @Transactional
    @Modifying
    @Query("SELECT e FROM MenuRestaurant e WHERE e.date= ?1 AND e.restaurant = ?2")
    MenuRestaurant getMenuByDateAndId(Date date, int id);
}
