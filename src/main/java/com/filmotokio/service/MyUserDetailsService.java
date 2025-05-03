package com.filmotokio.service;

import com.filmotokio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       com.filmotokio.model.User user = userRepository.findByEmail(username).orElseThrow(
                ()-> new UsernameNotFoundException("Utilizador nao encontrado"));
        return new User(user.getEmail(),user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+ user.getRole().name())));
    }
}
