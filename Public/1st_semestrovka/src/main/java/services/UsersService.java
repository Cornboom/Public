package services;

import dto.UserDto;
import models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<UserDto> getUserByCookie(String value);
    User getUserByEmail(String email);
    List<User> getAll();
    void addUser(User user);
}