package ru.manicure.service;

import ru.manicure.model.User;
import ru.manicure.model.dto.user.UserFullDto;
import ru.manicure.model.dto.user.UserUpdateDto;

public interface UserService {
    User createUser(UserFullDto userFullDto);

    User getById(Long userId);

    User update(Long userId, UserUpdateDto userUpdateDto);

    void removeById(Long userId);
}
