package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.InvalidUsernameOrPasswordException;
import com.tattooshop.shop.repository.UserRepository;
import com.tattooshop.shop.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUsernameOrPasswordException::new);
    }

}