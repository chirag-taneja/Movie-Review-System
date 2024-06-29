package com.movie.booking.system.repo;

import com.movie.booking.system.entity.Show;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface ShowRepo extends JpaRepository<Show,Long> {

    @Query(value = "select s.show_id , s.screen_number , s.show_time , s.movie_id , s.theatre_id , s2.seat_id , s2.seat_number , s2.status from \"show\" s join seat s2 on s.show_id = s2.show_id where s.show_id=?",nativeQuery = true)
    List<Show> getSeatForShow(Long showId);

    List<Show> findAllByMovieMovieId(long id);

    List<Show> findAllByTheatreTheatreId(long id);
}
