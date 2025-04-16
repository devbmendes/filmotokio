package com.filmotokio.service;

import com.filmotokio.model.User;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    public List<User> getAll();
    public User save(User user);
    public Optional<User> findById(Long id);
    public Boolean deleteById(Long id);
}
