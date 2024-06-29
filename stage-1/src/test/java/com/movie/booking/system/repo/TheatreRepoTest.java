package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Theatre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TheatreRepoTest {
    @Autowired
    TheatreRepo theatreRepo;

    @Test
    public void saveTheature()
    {
        Theatre theatre = Theatre.builder()
                .name("inox").address("gandhinagar")
                .city("gandhinagar").totalScreens(4)
                .build();
        Theatre save = theatreRepo.save(theatre);
        System.out.println(save);
    }

}