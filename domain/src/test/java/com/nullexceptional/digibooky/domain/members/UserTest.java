package com.nullexceptional.digibooky.domain.members;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    public void UserHasTheCorrectInformation(){
        User user = new User(UUID.randomUUID(),"123456",null,null,null,null);
        String inssOfUser = user.getInss();
        assertThat("123456").isEqualTo(inssOfUser);
    }
}