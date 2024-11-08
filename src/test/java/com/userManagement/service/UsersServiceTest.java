package com.userManagement.service;
import com.userManagement.entity.UsersEntity;
import com.userManagement.dto.UsersDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UsersServiceTest {

    @Autowired
    private UsersService usersService;

    @Test
    void fetchUserByUsername() {
        UsersEntity usersEntity = usersService.fetchUserByUsername("testUser");
        assertEquals(usersEntity.getFirstName(), "Test");

    }

    @Test
    void addNewUser() {
        UsersEntity usersEntity = usersService.addNewUser(new UsersDto("testUser", "Test", "User", "user"));
        assertEquals(usersEntity.getUsersId(), 5);
        assertEquals(usersEntity.getUsername(), "testUser");
    }

    @Test
    void fetchAllUsers() {
        List<UsersEntity> usersEntityList = usersService.fetchAllUsers();
        assertEquals(usersEntityList.size(), 4);
        assertEquals(usersEntityList.get(0).getFirstName(), "youcef");
    }
    

}