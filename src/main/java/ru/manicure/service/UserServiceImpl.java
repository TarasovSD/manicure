package ru.manicure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.manicure.exceptions.UserNotFoundException;
import ru.manicure.mapper.UserMapper;
import ru.manicure.model.User;
import ru.manicure.model.dto.user.UserFullDto;
import ru.manicure.model.dto.user.UserUpdateDto;
import ru.manicure.repository.UserRepository;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(UserFullDto userFullDto) {
        User createdUser = UserMapper.toUser(userFullDto);
        return userRepository.save(createdUser);
    }

    @Override
    public User getById(Long userId) {
        String message = "User id " + userId + " not found";
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(message));
    }

    @Override
    @Transactional
    public User update(Long userId, UserUpdateDto userUpdateDto) {
        User userToUpdate = getById(userId);

        if (userUpdateDto.getFirstName() != null) {
            if (!userUpdateDto.getFirstName().isBlank()) {
                userToUpdate.setFirstName(userUpdateDto.getFirstName());
            }
        }
        if (userUpdateDto.getLastName() != null) {
            if (!userUpdateDto.getLastName().isBlank()) {
                userToUpdate.setLastName(userUpdateDto.getLastName());
            }
        }
        if (userUpdateDto.getPatronymic() != null) {
            if (!userUpdateDto.getPatronymic().isBlank()) {
                userToUpdate.setPatronymic(userUpdateDto.getPatronymic());
            }
        }
        if (userUpdateDto.getEmail() != null) {
            if (!userUpdateDto.getEmail().isBlank()) {
                userToUpdate.setEmail(userUpdateDto.getEmail());
            }
        }
        if (userUpdateDto.getPhone() != null) {
            if (!userUpdateDto.getPhone().isBlank()) {
                userToUpdate.setPhone(userUpdateDto.getPhone());
            }
        }
        if (userUpdateDto.getBirthday() != null) {
            userToUpdate.setBirthday(userUpdateDto.getBirthday());
        }
        return userToUpdate;
    }

    @Override
    @Transactional
    public void removeById(Long userId) {
        User userToDelete = getById(userId);
        userRepository.delete(userToDelete);
    }

}
