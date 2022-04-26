package com.tattooshop.shop.service;

import com.tattooshop.shop.model.ScheduledTattoos;
import com.tattooshop.shop.model.User;

import java.util.Optional;

public interface ScheduledTattoosService {

    Optional<ScheduledTattoos> findByUser(User user);
}
