package com.nullexceptional.digibooky.service.members;

import com.nullexceptional.digibooky.domain.members.User;
import com.nullexceptional.digibooky.domain.members.UserRepository;
import com.nullexceptional.digibooky.domain.members.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    @Test
    void conversionFromUserToUserDto() {
        UserMapper userMapper = new UserMapper();
        User user = new User(UUID.randomUUID(), "123465", "Omar", "Del Oro", "nullE@pom.xml", null);
        UserDto userDto = userMapper.toUserDTO(user);
        assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
    }

    @Test
    void conversionFromUsersListToUsersDtoList() {
        UserMapper userMapper = new UserMapper();
        UserRepository userRepository = new UserRepository();

        List<User> allUsers = userRepository.getAllUsers();
        User userFromList = allUsers.stream().filter(user -> user.getFirstName().equals("John")).findFirst().orElse(null);
        List<UserDto> userDtos = userMapper.fromUsersToUsersDtoList(userRepository.getAllUsers());
        UserDto userDtoFromList = userDtos.stream().filter(user -> user.getFirstName().equals("John")).findFirst().orElse(null);

        assertThat(allUsers.size()).isEqualTo(userDtos.size());
        assertThat(userFromList.getId()).isEqualTo(userDtoFromList.getId());

    }


    @Test
    void conversionFromUserDtoToUser() {
        UserMapper userMapper = new UserMapper();
        UserDto userDto = new UserDto(UUID.randomUUID(), "123465", "Omar", "Del Oro", "nullE@pom.xml", null);
        User user = userMapper.toUser(userDto);
        assertThat(userDto.getId()).isEqualTo(user.getId());
    }
}