package ru.javaops.topjava2.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.MenuRestaurant;
import ru.javaops.topjava2.repository.MenuRestaurantRepository;
import ru.javaops.topjava2.repository.UserVotingRepository;

import java.time.LocalTime;
import java.util.Date;

@RestController
@RequestMapping(value = UserVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVotingController {
    static final String REST_URL = "/api/users/vote";
    private static final LocalTime DEADLINE_TIME = LocalTime.of(20, 0, 0);
    @Autowired
    UserVotingRepository repository;

    @GetMapping("/{rest_id}")
    public void voteForRestaurant(@PathVariable int restId) {


//        if (LocalTime.now().isBefore(DEADLINE_TIME)) {
//            menuRestaurant.increaseVote();
//            repository.save(menuRestaurant);
        }
    }
