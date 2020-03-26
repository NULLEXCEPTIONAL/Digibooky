package com.nullexceptional.digibooky.domain.members;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDto {
    int insertUser(User user);
    List<User> selectAllUsers();
    int updateUserById(UUID id, User professor);
    Optional<User> selectUserById(UUID id);
    Optional<User> selectUserByInss(String inss);

}
