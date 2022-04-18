package com.tattooshop.shop.service.impl;

import com.tattooshop.shop.model.Role;
import com.tattooshop.shop.model.User;
import com.tattooshop.shop.model.exceptions.InvalidUsernameOrPasswordException;
import com.tattooshop.shop.model.exceptions.MyUniversalException;
import com.tattooshop.shop.model.exceptions.PasswordsDoNotMatchException;
import com.tattooshop.shop.model.exceptions.UsernameAlreadyExistsException;
import com.tattooshop.shop.repository.UserRepository;
import com.tattooshop.shop.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow( () -> new UsernameNotFoundException(s));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username.isEmpty() || username == null || password.isEmpty() || password == null)
            throw new InvalidUsernameOrPasswordException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,this.passwordEncoder.encode(password),name,surname,role);
        return this.userRepository.save(user);

    }
}
