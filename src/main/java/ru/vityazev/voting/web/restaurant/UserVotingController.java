package ru.vityazev.voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private static LocalTime DEADLINE_TIME = LocalTime.of(11, 00, 0);

    @PutMapping("/{id}")
    public void revote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        LocalDate dayToday = LocalDate.now();
        LocalTime timeToday = LocalTime.now();
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        Optional<UserVoting> optionalUserVoting = userVotingRepository.findByUserAndDate(user.getId(), dayToday);
        boolean check = optionalUserVoting.isPresent();
        if (optionalRestaurant.isPresent() && optionalUserVoting.isPresent()) {
            if (timeToday.isBefore(DEADLINE_TIME) && check) {
                UserVoting userVoting = optionalUserVoting.get();
                userVoting.setRestaurant(optionalRestaurant.get());
                userVotingRepository.save(userVoting);
            }
        }
    }

    @GetMapping
    ResponseEntity<UserVoting> get(@AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        int userId = user.getId();
        return ResponseEntity.of(userVotingRepository.findByUserAndDate(userId, LocalDate.now()));
    }

    @PostMapping("/{id}")
    public void vote(@PathVariable int id, @AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        LocalDate dayToday = LocalDate.now();
        boolean check = userVotingRepository.findByUserAndDate(user.getId(), dayToday).isPresent();
        if (!check) {
            restaurantRepository.findById(id)
                    .map(restaurant -> new UserVoting(user, restaurant))
                    .ifPresent(votedUser -> userVotingRepository.save(votedUser));
        }
    }
}


