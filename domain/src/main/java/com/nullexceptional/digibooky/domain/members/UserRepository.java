package com.nullexceptional.digibooky.domain.members;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
}
