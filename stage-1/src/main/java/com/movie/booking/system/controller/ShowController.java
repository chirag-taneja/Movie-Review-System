package com.movie.booking.system.controller;

import com.movie.booking.system.dto.ShowDto;
import com.movie.booking.system.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {
    public  final ShowService showService;

    //get all show for movie based on id
    @GetMapping("/movie")
    public ResponseEntity<Object> getAllShowForMovie(@RequestParam long movieId)
    {
       return showService.getAllShowForMovie(movieId);
    }

    // get all show on that theatre id

    @GetMapping("/theatre")
    public ResponseEntity<Object> getAllShowRunByTheatreActive(@RequestParam long theatreId)
    {
        return showService.getAllShowRunByTheatreActive(theatreId);
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findShowById(@PathVariable long id){
        return showService.findShowById(id);
    }



    //give set of all avialble seat for that show
    @GetMapping("/available-seat")
    public ResponseEntity<Object> findSeatAvailable(@RequestParam long showId)
    {
       return showService.findSeatAvailable(showId);
    }

    //get all seats

    //save show
    @PostMapping
    public ResponseEntity<Object> saveShow(@RequestBody ShowDto showDto) throws ParseException {
          return showService.saveShow(showDto);
    }
}
