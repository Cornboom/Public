package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.springbootdemo.dto.UserDto.*;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = usersRepository.getOne(userId);
        return UserDto.from(user);
    }

    @Override
    public List<UserDto> findAll() {
        return usersRepository.findAll().stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
