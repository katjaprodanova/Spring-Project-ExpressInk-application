package com.tattooshop.shop.service;

import com.tattooshop.shop.model.User;

public interface AuthService {
    User login(String username, String password);
}
