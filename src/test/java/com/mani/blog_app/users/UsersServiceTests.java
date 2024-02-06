package com.mani.blog_app.users;

import com.mani.blog_app.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {
    @Autowired
    UsersService usersService;
    // if i don't want use the JPA database... it get repository Entitys
   // @MockBean
  // 

    @Test
    void can_create_users(){
        var user = usersService.createUser(new CreateUserRequest(
                "mani",
                "pass123",
                "mani@blog.com"
        ));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("mani", user.getUsername());
    }
}
