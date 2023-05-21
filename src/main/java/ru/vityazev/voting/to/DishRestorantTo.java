package ru.vityazev.voting.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Collection;

public class DishRestorantTo {

    private Integer restId;

    private Collection<Integer> dishes;

}
