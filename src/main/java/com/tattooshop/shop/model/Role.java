package com.tattooshop.shop.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ARTIST, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}