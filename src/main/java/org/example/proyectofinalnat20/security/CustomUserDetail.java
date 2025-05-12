package org.example.proyectofinalnat20.security;

import org.example.proyectofinalnat20.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetail implements UserDetails {

    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var auths = new ArrayList<SimpleGrantedAuthority>();
        var simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().getName());
        auths.add(simpleGrantedAuthority);
        return auths;
    }
}
