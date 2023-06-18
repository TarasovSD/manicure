package ru.manicure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.manicure.mapper.UserMapper;
import ru.manicure.model.dto.user.UserFullDto;
import ru.manicure.model.dto.user.UserUpdateDto;
import ru.manicure.service.UserService;

import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Slf4j
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UserFullDto create(@RequestBody UserFullDto userDto) {
        log.info("A request to create a user has been sent to the userService");
        return UserMapper.toUserFullDto(userService.createUser(userDto));
    }

    @GetMapping("/{userId}")
    public UserFullDto getById(@PositiveOrZero @PathVariable Long userId) {
        log.info("A request to get a user with id {} has been sent to the userService", userId);
        return UserMapper.toUserFullDto(userService.getById(userId));
    }

    @PatchMapping("/{userId}")
    public UserFullDto update(@PositiveOrZero @PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        log.info("A request to update a user with id {} has been sent to the userService", userId);
        return UserMapper.toUserFullDto(userService.update(userId, userUpdateDto));
    }

    @DeleteMapping("/{userId}")
    public void removeById(@PositiveOrZero @PathVariable Long userId) {
        log.info("A request to delete a user with id {} has been sent to the userService", userId);
        userService.removeById(userId);
    }
}
