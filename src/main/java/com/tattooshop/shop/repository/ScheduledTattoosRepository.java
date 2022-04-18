package com.tattooshop.shop.repository;

import com.tattooshop.shop.model.ScheduledTattoos;
import com.tattooshop.shop.model.Tattoo;
import com.tattooshop.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduledTattoosRepository extends JpaRepository<ScheduledTattoos,Long> {
    Optional<ScheduledTattoos> findByUser(User user);
    //List<Tattoo> findByDateTime(LocalDateTime dateTime);

}
