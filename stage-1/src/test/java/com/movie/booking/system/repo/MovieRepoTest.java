package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepoTest {

    @Autowired
    MovieRepo movieRepo;

    @Test
    public void saveMovie()
    {
        Movie build = Movie.builder()
                .duration("2:24")
                .title("animal")
                .genre("adult")
                .language("hindi")
                .releaseDate(new Date(2024,1,2))
                .build();
        movieRepo.save(build);
    }

    @Test
    public void getAll()
    {
        List<Movie> all = movieRepo.findAll();
        for (Movie movie : all) {
            System.out.println(movie.getTitle());
        }
    }
}