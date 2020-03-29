package com.nullexceptional.digibooky.domain.members;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    private Map<UUID, User> db;

    public UserRepository() {
        this.db = new HashMap<>();
        dummyMethodToAddUsers();
    }

    public User saveUser(User user) {
        db.put(user.getId(), user);
        return getUserById(user.getId());
    }

    public List<User> getAllUsers() {
        return db.values().stream()
                .collect(Collectors.toList());
    }

    public User getUserById(UUID id) {
        return db.values().stream()
                .filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User getUserByInss(String inss) {
        return db.values().stream()
                .filter(user1 -> user1.getInss().equals(inss))
                .findFirst()
                .orElse(null);

    }


    //add default Users for testing
    private void dummyMethodToAddUsers() {
        db.put(UUID.randomUUID(), new User(UUID.randomUUID(), "465321", "Poppy", "Mommy", "poppy@gmail.com", null));
        db.put(UUID.randomUUID(), new User(UUID.randomUUID(), "122345", "John", "McFly", "john@gmail.com", null));
        db.put(UUID.randomUUID(), new User(UUID.randomUUID(), "457896", "Yuppi", "Hummy", "yuppi@gmail.com", null));
        db.put(UUID.randomUUID(), new User(UUID.randomUUID(), "236325", "Kiri", "Niri", "Kiri@gmail.com", null));
    }
}
