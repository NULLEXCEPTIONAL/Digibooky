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

    public User saveUser(User user){
       db.put(user.getId(),user);
       return getUserById(user.getId());
    }

    public List<User> getAllUsers(){
        return db.values().stream()
                .collect(Collectors.toList());
    }

    public User getUserById(UUID id){
        return db.values().stream()
                 .filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
