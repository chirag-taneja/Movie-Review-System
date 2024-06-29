package com.movie.booking.system.service;

import com.movie.booking.system.dto.ShowDto;
import com.movie.booking.system.entity.Movie;
import com.movie.booking.system.entity.Seat;
import com.movie.booking.system.entity.Show;
import com.movie.booking.system.entity.Theatre;
import com.movie.booking.system.repo.MovieRepo;
import com.movie.booking.system.repo.ShowRepo;
import com.movie.booking.system.repo.TheatreRepo;
import com.movie.booking.system.util.SeatStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {
    public  final ShowRepo showRepo;
    public  final MovieRepo movieRepo ;
    public  final TheatreRepo theatreRepo;
    public ResponseEntity<Object> getAllShowForMovie(long movieId) {
        List<Show> allByMovieMovieId = showRepo.findAllByMovieMovieId(movieId);
        return ResponseEntity.ok(allByMovieMovieId);
    }

    public ResponseEntity<Object> getAllShowRunByTheatreActive(long theatreId) {
        List<Show> allByMovieMovieId = showRepo.findAllByTheatreTheatreId(theatreId);
        return ResponseEntity.ok(allByMovieMovieId);
    }

    public ResponseEntity<Object> findShowById(long id) {
        if (showRepo.existsById(id)) {
            Show show = showRepo.findById(id).get();
            return ResponseEntity.ok(show);
        }
        else {
            throw new RuntimeException("theatre not found with this id");
        }
    }

    public ResponseEntity<Object> findSeatAvailable(long showId) {
        if (showRepo.existsById(showId)) {
            Set<Seat> collect = showRepo.findById(showId).get().getSeatSet().stream().filter(seat -> seat.getStatus() == SeatStatus.AVAILABLE).collect(Collectors.toSet());
            return ResponseEntity.ok(collect);
        }
        else {
            throw new RuntimeException("theatre not found with this id");
        }
    }

    public ResponseEntity<Object> saveShow(ShowDto showDto) throws ParseException {
        String dateFormat="dd/MM/yyyy";
        String showTime = showDto.showTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
        Date parseDate = simpleDateFormat.parse(showTime);
        Optional<Movie> movie =  movieRepo.findById(showDto.movieId());
        Optional<Theatre> theatre = theatreRepo.findById(showDto.theatreId());
        Show build = Show.builder()
                .movie(movie.get())
                .theatre(theatre.get())
                .screenNumber(showDto.screenNo())
                .showTime(parseDate)
                .build();
        showRepo.save(build);
        return ResponseEntity.status(HttpStatus.CREATED).body("Show created successfully");
    }
}
