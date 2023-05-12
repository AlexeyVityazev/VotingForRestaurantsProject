package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "restaurant_menu")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenu implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;


    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Id
    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date date = new Date();


}

//    Composite Primary Key: https://www.objectdb.com/java/jpa/entity/id#composite_primary_key