package com.movie.booking.system.service;

import com.movie.booking.system.dto.MovieDto;
import com.movie.booking.system.entity.Movie;
import com.movie.booking.system.repo.MovieRepo;
import com.movie.booking.system.vo.MovieVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    public final MovieRepo movieRepo;
    public ResponseEntity<Object> getAllMovies() {
        List<MovieVo> collect = movieRepo.findAll().stream().map(i -> new MovieVo(i.getMovieId(), i.getTitle(), i.getGenre(), i.getDuration(), i.getLanguage(), i.getReleaseDate(), i.isActiveFlag())).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<Object> getMovieById(long id) {
        if (movieRepo.existsById(id)) {
            Movie i = movieRepo.findById(id).get();
            MovieVo movieVo = new MovieVo(i.getMovieId(), i.getTitle(), i.getGenre(), i.getDuration(), i.getLanguage(), i.getReleaseDate(), i.isActiveFlag());
            return ResponseEntity.ok(movieVo);
        }
        else {
            throw new RuntimeException("Movie not found with this id");
        }
    }

    public ResponseEntity<Object> getMovieByName(String movieName) {
        List<MovieVo> collect = movieRepo.findByTitle(movieName).stream().map(i -> new MovieVo(i.getMovieId(), i.getTitle(), i.getGenre(), i.getDuration(), i.getLanguage(), i.getReleaseDate(), i.isActiveFlag())).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<Object> saveMovie(MovieDto movieDto) throws ParseException {

        String dateFormat="dd/MM/yyyy";
        String releaseDateStr = movieDto.releaseDate();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);

        Date parseDate = simpleDateFormat.parse(releaseDateStr);


        Movie build = Movie.builder()
                .duration(movieDto.duration())
                .title(movieDto.title())
                .genre(movieDto.genre())
                .language(movieDto.language())
                .releaseDate(parseDate)
                .activeFlag(true)
                .build();
        movieRepo.save(build);
        return ResponseEntity.status(HttpStatus.CREATED).body("Movie created successfully");
    }
}
