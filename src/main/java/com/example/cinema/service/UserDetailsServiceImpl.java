package com.example.cinema.service;

import com.example.cinema.dao.CompteRepository;
import com.example.cinema.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CompteRepository compteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Compte c = compteRepository.findByUsername(username);
        if (c==null) throw new UsernameNotFoundException(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(c.getRole().getRole()));
        return new User(c.getUsername(),c.getPassword(), authorities);
    }
}
