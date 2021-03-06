package com.nullexceptional.digibooky.service.members;

import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toUserDTO(User user) {
        return new UserDto(user.getId(),user.getInss(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress());
    }


    public List<UserDto> fromUsersToUsersDtoList(List<User> usersList) {
        return usersList.stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());
    }

    public UserDto fromUserToUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(),user.getInss(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress());
        return userDto;
    }


    public User toUser(UserDto user) {
        return new User(user.getId(), user.getInss(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress());
    }


}
