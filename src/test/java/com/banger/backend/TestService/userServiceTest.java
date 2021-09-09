package com.banger.backend.TestService;

import com.banger.backend.DTO.userDTO;
import com.banger.backend.Entity.User;
import com.banger.backend.Service.userService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class userServiceTest {

    @Autowired
    private userService userService;

    userDTO dto;

    @Test
    public void getAllUsers(){
        List<userDTO> userDTOList= userService.getAllAcceptedUsersToList();
        assertThat(userDTOList,not(IsEmptyCollection.empty()));
    }

    @Test
    public void getAllBlackListedUsers(){
        List<userDTO> userDTOList= userService.getAllBlackListedUsersToList();
        assertThat(userDTOList,not(IsEmptyCollection.empty()));
    }

}
