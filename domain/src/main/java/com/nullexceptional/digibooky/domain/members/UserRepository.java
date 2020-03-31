package com.nullexceptional.digibooky.domain.members;

import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailDuplicationExceptionRequest;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailRequestException;
import com.nullexceptional.digibooky.domain.members.exceptions.DuplicationInssException;
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
    public void isInssNotUnique(String  inss) {
        boolean isInssNotUnique = db.values().stream()
                .anyMatch(user -> user.getInss().equals(inss));
        if (isInssNotUnique) {
            throw new DuplicationInssException("This inss number already exists. This is a custom message");
        }
    }

    public void isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new ApiEmailRequestException("E-mail is not valid. This is a custom exception");
        }
    }

    public void isEmailUnique(String email) {
        boolean isEmailUnique = db.values().stream()
                .anyMatch(x -> x.getEmail().equals(email));
        if (isEmailUnique) {
            throw new ApiEmailDuplicationExceptionRequest("E-mail already exists in our system. Of course this is a custom message");
        }
    }
}
