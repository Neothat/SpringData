package ru.geekbrains.springdata.services;

import ru.geekbrains.springdata.dto.RegRequest;

public interface RegService {
    void createNewUser(RegRequest regRequest);
}
