package com.nullexceptional.digibooky.service.members;

import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers(){
       return userMapper.fromUserToUserDto(userRepository.getAllUsers());
    }

    public void addUser(UserDto userDto) {
        userRepository.saveUser(userMapper.toUser(userDto));
    }

    public UserDto getUserById(UUID id) {
        return userMapper.fromUserToUserDto(userRepository.getUserById(id));
    }
}
