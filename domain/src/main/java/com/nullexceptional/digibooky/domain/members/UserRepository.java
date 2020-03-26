package com.nullexceptional.digibooky.domain.members;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository implements UserDto {
    private List<User> db = new ArrayList<>();

    @Override
    public int insertUser(User user) {
        db.add(user);
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return db;
    }

    @Override
    public int updateUserById(UUID id, User professor) {
        return 0;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> selectUserByInss(String inss) {
        return Optional.empty();
    }
}
