package com.filmotokio.service;

import com.filmotokio.DTO.UserDto;
import com.filmotokio.model.User;
import com.filmotokio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserInterface{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserDto userDto) {

        if(userDto.getPassword().equals(userDto.getPasswordMatch())){
            User user = new User();
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setEmail(userDto.getEmail());
            user.setBirthDate(userDto.getBirthDate());
            user.setRegistrationDate(new Date());
            user.setPassword(userDto.getPassword());
            user.setImage(userDto.getImage());
            return userRepository.save(user);
        }else{
            return new User();
        }
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
