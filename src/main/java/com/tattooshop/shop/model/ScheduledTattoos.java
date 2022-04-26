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

    @OneToOne
    private Tattoo selectedTattoo;

    private LocalDateTime dateTime;

    public ScheduledTattoos() {
    }

    public ScheduledTattoos(User user, Tattoo selectedTattoo,LocalDateTime dateTime) {
        this.user = user;
        this.selectedTattoo = selectedTattoo;
        this.dateTime = dateTime;
    }
}
