package ru.geekbrains.springdata.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.geekbrains.springdata.entities.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> findByUsername(String username);

    public UserDetails loadUserByUsername(String username);

}
