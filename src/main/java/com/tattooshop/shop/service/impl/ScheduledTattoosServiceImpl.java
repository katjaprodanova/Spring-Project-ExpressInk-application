package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.ScheduledTattoos;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.UserNotFoundException;
import com.tattooshop.shop.repository.ScheduledTattoosRepository;
import com.tattooshop.shop.service.ScheduledTattoosService;
import com.tattooshop.shop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduledTattoosServiceImpl implements ScheduledTattoosService {

    private final ScheduledTattoosRepository scheduledTattoosRepository;
    private final UserService userService;

    public ScheduledTattoosServiceImpl(ScheduledTattoosRepository scheduledTattoosRepository, UserService userService) {
        this.scheduledTattoosRepository = scheduledTattoosRepository;
        this.userService = userService;
    }

    @Override
    public Optional<ScheduledTattoos> findByUser(User user) {
        return this.scheduledTattoosRepository.findByUser(user);
    }
}
