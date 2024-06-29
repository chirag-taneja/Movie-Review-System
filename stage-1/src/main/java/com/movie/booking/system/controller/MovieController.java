package com.movie.booking.system.controller;

import com.movie.booking.system.service.MovieService;
import com.movie.booking.system.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    public final MovieService movieService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getMovieById(@PathVariable long id)
    {
        return movieService.getMovieById(id);
    }

    @GetMapping("/name")
        public ResponseEntity<Object> getMovieByName(@RequestParam String movieName)
    {
        return movieService.getMovieByName(movieName);
    }

    @PostMapping
    public ResponseEntity<Object> saveMovie(@RequestBody MovieDto movieDto) throws ParseException {
        return movieService.saveMovie(movieDto);
    }


}
