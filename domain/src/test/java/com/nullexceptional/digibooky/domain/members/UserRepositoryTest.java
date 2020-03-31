package com.nullexceptional.digibooky.domain.members;

import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailDuplicationExceptionRequest;
import com.nullexceptional.digibooky.domain.members.exceptions.ApiEmailRequestException;
import com.nullexceptional.digibooky.domain.members.exceptions.DuplicationInssException;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
    @Test
    void getAllUsers() {
        UserRepository userRepository = new UserRepository();
        List<User> allUsers = userRepository.getAllUsers();
        int actualSizeOfList = allUsers.size();
        User userByInss = userRepository.getUserByInss("122345");
        assertEquals(4, actualSizeOfList);
        assertEquals("John", userByInss.getFirstName());
    }

    @Test
    void getUserById() {
        UserRepository userRepository = new UserRepository();
        User userByInss = userRepository.getUserByInss("236325");
        assertEquals("Kiri", userByInss.getFirstName());
    }

    @Test
    void saveUser() {
        UserRepository userRepository = new UserRepository();
        User user = new User(UUID.randomUUID(), "123456", "Yesman", "Exceptional", "null@ehb.be", null);
        User userFromDb = userRepository.saveUser(user);
        assertEquals("Yesman", userFromDb.getFirstName());
    }

    @Test
    void whenUserInssIsNotUnique() {
        UserRepository userRepository = new UserRepository();
        assertThatThrownBy(() -> userRepository.isInssNotUnique("122345"))
                .isInstanceOf(DuplicationInssException.class);
    }

    @Test
    void whenUserEmailIsNotUnique() {
        UserRepository userRepository = new UserRepository();
        assertThatThrownBy(() -> userRepository.isEmailUnique("john@gmail.com"))
                .isInstanceOf(ApiEmailDuplicationExceptionRequest.class);
    }

    @Test
    void whenUserEmailIsNotValid() {
        UserRepository userRepository = new UserRepository();
        assertThatThrownBy(() -> userRepository.isValidEmailAddress("john@"))
                .isInstanceOf(ApiEmailRequestException.class);
    }

}