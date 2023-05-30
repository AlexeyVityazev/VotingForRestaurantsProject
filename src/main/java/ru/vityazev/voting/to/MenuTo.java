package ru.vityazev.voting.to;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MenuTo {
    private int idRestaurant;
    private int idDish;
    private LocalDate date;
}
