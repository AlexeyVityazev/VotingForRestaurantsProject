package ru.vityazev.voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vityazev.voting.model.Restaurant;
import ru.vityazev.voting.model.User;
import ru.vityazev.voting.model.UserVoting;
import ru.vityazev.voting.repository.RestaurantRepository;
import ru.vityazev.voting.repository.UserVotingRepository;
import ru.vityazev.voting.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = UserVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVotingController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserVotingRepository userVotingRepository;
    static final String REST_URL = "/api/users/votes";
    private static LocalTime DEADLINE_TIME = LocalTime.of(11, 0, 0);
    private LocalTime timeToday = LocalTime.now();
    private LocalDate dayToday = LocalDate.now();

    @PostMapping("/{id}")
    public void voteForRestaurant(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        if (timeToday.isBefore(DEADLINE_TIME)) {
            int user_id = authUser.getUser().getId();
            User user = authUser.getUser();

            Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
            Restaurant restaurant = optionalRestaurant.get();
            boolean check = userVotingRepository.findByUserAndDate(user_id, dayToday).isPresent();

            if (!check) {
                UserVoting votedUser = new UserVoting(user, restaurant);
                userVotingRepository.save(votedUser);
            } else {
                Optional<UserVoting> optionalUserVoting = userVotingRepository.findByUserAndDate(user_id, dayToday);
                UserVoting userVoting = optionalUserVoting.get();
                userVoting.setRestaurant(restaurant);
                userVotingRepository.save(userVoting);
            }
        }
    }

    @GetMapping("/{id}")
    public UserVoting get(@PathVariable int id) {
        Optional<UserVoting> optional = userVotingRepository.findByUserAndDate(id,LocalDate.now());
        UserVoting userVoting = optional.get();
        return userVoting;
    }
}


