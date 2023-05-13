package ru.javaops.topjava2.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.MenuRestaurant;
import ru.javaops.topjava2.repository.MenuRestaurantRepository;

import java.time.LocalTime;
import java.util.Date;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/api/users/vote";
    @Autowired
    MenuRestaurantRepository repository;

    @PostMapping(name = "/{rest_id}")
    public void voteForRestaurant(@PathVariable int id) {
        MenuRestaurant menuRestaurant = repository.getMenuByDateAndId(new Date(), id);
        Date today = new Date();
        LocalTime time1, time2;
        time1 = LocalTime.of(11, 00, 00);
        time2 = LocalTime.of(today.getHours(), today.getMinutes(), today.getSeconds());
        int returnVal = time1.compareTo(time2);

        if (returnVal <= 0) {
            menuRestaurant.increaseVote();
        }
    }
}