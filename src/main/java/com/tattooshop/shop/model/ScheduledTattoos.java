package com.tattooshop.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ScheduledTattoos {

    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Tattoo> selectedTattoos;

    private LocalDateTime dateTime;

    public ScheduledTattoos() {
    }

    public ScheduledTattoos(User user, LocalDateTime dateTime) {
        this.user = user;
        this.selectedTattoos = new ArrayList<>();
        this.dateTime = dateTime;
    }
}
