package com.movie.booking.system.repo;

import com.movie.booking.system.entity.User;
import com.movie.booking.system.util.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepoTest {

    @Autowired
    UserRepo userRepo;
    @Test
    public void saveUser()
    {
        User build = User.builder()
                .email("ram@gmail.conm").role(Role.USER)
                .username("ram").password("a")
                .build();
        userRepo.save(build);
    }

    @Test
    public void getAll()
    {
        List<User> all = userRepo.findAll();
        all.forEach(i-> System.out.println(i.getUsername()));
    }

}