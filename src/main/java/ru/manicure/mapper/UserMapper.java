package ru.manicure.mapper;

import lombok.experimental.UtilityClass;
import ru.manicure.model.User;
import ru.manicure.model.dto.user.UserFullDto;

@UtilityClass
public class UserMapper {

    public User toUser(UserFullDto userFullDto) {
        return new User(userFullDto.getId(),
                userFullDto.getFirstName(),
                userFullDto.getLastName(),
                userFullDto.getPatronymic(),
                userFullDto.getEmail(),
                userFullDto.getPhone(),
                userFullDto.getBirthday(),
                userFullDto.getIsMaster());
    }

    public UserFullDto toUserFullDto(User user) {
        return new UserFullDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthday(),
                user.getIsMaster());
    }
}
