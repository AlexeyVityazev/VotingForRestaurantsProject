package ru.vityazev.voting.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vityazev.voting.model.MenuRestaurant;
import ru.vityazev.voting.repository.MenuRestaurantRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = MenuRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestaurantController {
    static final String REST_URL = "/api/menuRestaurant";
    @Autowired
    MenuRestaurantRepository repository;

    @GetMapping
    public List<MenuRestaurant> getAllMenuForToday() throws ParseException {
        log.info("getAll menu for today");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        Date todayWithZeroTime = formatter.parse(formatter.format(today));

        List<MenuRestaurant> allMenuByDate = repository.findMenusByDate(todayWithZeroTime);
        return allMenuByDate;
    }

}
