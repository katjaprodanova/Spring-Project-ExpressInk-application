package com.tattooshop.shop.service;

import com.tattooshop.shop.model.Role;
import com.tattooshop.shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    List<User> findAll();
}
