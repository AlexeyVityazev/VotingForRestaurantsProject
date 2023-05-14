package ru.javaops.topjava2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_voting")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVoting extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    @Column(name = "date", nullable = false, columnDefinition = "date default now()", updatable = false)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date date = new Date();
}
