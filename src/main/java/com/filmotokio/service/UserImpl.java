package com.filmotokio.service;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.exception.ResourceNotFoundException;
import com.filmotokio.model.User;
import com.filmotokio.model.UserRole;
import com.filmotokio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserInterface{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new RuntimeException("Esse email ja existe");
        }
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setBirthDate(userDto.getBirthDate());
            if("ADMIN".equalsIgnoreCase(userDto.getUserType())){
                user.setRole(UserRole.ADMIN);
            }else {
                user.setRole(UserRole.USER);
            }
            user.setRegistrationDate(new Date());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Boolean deleteById(Long id)
    {
        if (findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
