package com.movie.booking.system.controller;

import com.movie.booking.system.dto.TheatreDto;
import com.movie.booking.system.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
@RequiredArgsConstructor
public class TheatreController {

    public final TheatreService theatreService;

    //get all theature

    @GetMapping("/list")
    public ResponseEntity<Object> getAllTheatre() {
        return theatreService.getAllTheatre();
    }

    ///get theature by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTheatreById(@PathVariable long id) {
        return theatreService.getTheatreById(id);
    }

    //get theature by location
    @GetMapping("/near")
    public ResponseEntity<Object> getTheatreNearBy(@RequestParam String city) {
        return theatreService.getTheatreNearBy(city);
    }

    //save theature
    @PostMapping
    public ResponseEntity<Object> saveTheatre(@RequestBody TheatreDto theatreDto) {
        return theatreService.saveTheatre(theatreDto);
    }
}
