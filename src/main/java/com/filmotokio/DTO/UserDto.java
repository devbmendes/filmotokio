package com.filmotokio.DTO;

import lombok.*;

import java.util.Date;

public class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String passwordMatch;
    private Date birthDate;
    private String userRole;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPasswordMatch() {
        return passwordMatch;
    }

    public void setPasswordMatch(String passwordMatch) {
        this.passwordMatch = passwordMatch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto(String image, String userRole, Date birthDate, String passwordMatch, String password, String email, String surname, String name) {
        this.image = image;
        this.userRole = userRole;
        this.birthDate = birthDate;
        this.passwordMatch = passwordMatch;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
    }
}
