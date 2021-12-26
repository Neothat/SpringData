package ru.geekbrains.springdata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.springdata.dto.RegRequest;
import ru.geekbrains.springdata.entities.User;
import ru.geekbrains.springdata.repositories.UserRepository;
import ru.geekbrains.springdata.services.RegService;
import ru.geekbrains.springdata.services.UserService;

public class RegServiceImpl implements RegService {

    private UserService userService;
    private UserRepository userRepository;

    @Override
    public void createNewUser(RegRequest regRequest) {
        if (!userService.findByUsername(regRequest.getUsername()).isPresent()) {
            userRepository.save(new User(regRequest.getUsername(), regRequest.getPassword(), regRequest.getEmail()));
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
