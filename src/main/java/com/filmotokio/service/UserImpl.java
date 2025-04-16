package com.filmotokio.service;

import com.filmotokio.model.User;
import com.filmotokio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserImpl implements UserInterface{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow());
    }

    @Override
    public Boolean deleteById(Long id) {
        return findById(id).isPresent();
    }
}
