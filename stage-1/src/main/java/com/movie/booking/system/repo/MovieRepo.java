package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

//    @Query(value = "select * from movie m where m.title ilike '?'",nativeQuery = true)
//    List<Movie> getListOfMovieWithName(String name);

    List<Movie> findByTitle(String name);
}
