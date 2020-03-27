package com.nullexceptional.digibooky.domain.members;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {
    private Map<UUID,User> db;

    public UserRepository() {
        this.db = new HashMap<>();
    }

    public void saveUser(User user){
        db.put(user.getId(),user);
    }

    public List<User> getAllUsers(){
        return db.values().stream()
                .collect(Collectors.toList());
    }

    public User getUserById(UUID id){
       User user1 = db.values().stream()
                .filter(user -> user.getId().equals(id)).findFirst().orElse(null);
       return user1;
    }
}
