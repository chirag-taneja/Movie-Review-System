package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Movie;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.entity.Theatre;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShowRepoTest {


    ShowRepo showRepo; MovieRepo movieRepo ;TheatreRepo  theatreRepo;

    @Autowired
    public ShowRepoTest(ShowRepo showRepo, MovieRepo movieRepo, TheatreRepo theatreRepo) {
        this.showRepo = showRepo;
        this.movieRepo = movieRepo;
        this.theatreRepo = theatreRepo;
    }

    @Test
    public void saveShow()
    {
        Optional<Movie> movie =  movieRepo.findById(2L);
        Optional<Theatre> theatre = theatreRepo.findById(2L);
        Date showTime=new Date(2024,6,22,10,50);
        Show build = Show.builder()
                .movie(movie.get())
                .theatre(theatre.get())
                .screenNumber(1)
                .showTime(showTime)
                .build();
        showRepo.save(build);
    }

    @Test
    public void TestNativeQuery()
    {
        List<Show> seatForShow = showRepo.getSeatForShow(3L);
        System.out.println(seatForShow.get(0).getShowId());
    }


}