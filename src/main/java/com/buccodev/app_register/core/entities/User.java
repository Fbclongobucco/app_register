package com.buccodev.app_register.core.entities;

import com.buccodev.app_register.core.exception.EmailValidationException;
import com.buccodev.app_register.core.exception.PasswordValidationException;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private Long id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private Boolean isActive;

    public User(Long id, String name, LocalDate birthday, String email, String password, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.setEmail(email);
        this.setPassword(password);
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.emailValidation(email);
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        passwordValidate(password);
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void emailValidation(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailValidationException("Email cannot be empty or null!");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new EmailValidationException("Invalid email format!");
        }
    }


    public void passwordValidate(String password) {
        if (password.length() < 6 || password.length() > 100) {
            throw new PasswordValidationException("password cannot be less than 6 digits or larger than 100!");
        }
    }

    public Boolean ifThePasswordMatches(String password){
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
