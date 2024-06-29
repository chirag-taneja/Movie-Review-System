package com.movie.booking.system.service;

import com.movie.booking.system.dto.TheatreDto;
import com.movie.booking.system.entity.Theatre;
import com.movie.booking.system.repo.TheatreRepo;
import com.movie.booking.system.vo.TheatreVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheatreService {

    public final TheatreRepo theatreRepo;

    public ResponseEntity<Object> getAllTheatre() {
        List<TheatreVo> collect = theatreRepo.findAll().stream().map(i -> new TheatreVo(i.getTheatreId(), i.getName(), i.getCity(), i.getAddress(), i.getTotalScreens(), i.isActiveFlag())).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<Object> getTheatreById(long id) {
        if (theatreRepo.existsById(id)) {
            Theatre i = theatreRepo.findById(id).get();
            TheatreVo theatreVo = new TheatreVo(i.getTheatreId(), i.getName(), i.getCity(), i.getAddress(), i.getTotalScreens(), i.isActiveFlag());
            return new ResponseEntity<>(theatreVo, HttpStatus.OK);
        }
        else{
            throw new RuntimeException("theatre not found with this id");
        }
    }

    public ResponseEntity<Object> getTheatreNearBy(String city) {
        List<TheatreVo> collect = theatreRepo.findByCity(city).stream().map(i -> new TheatreVo(i.getTheatreId(), i.getName(), i.getCity(), i.getAddress(), i.getTotalScreens(), i.isActiveFlag())).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<Object> saveTheatre(TheatreDto theatreDto) {
        Theatre theatre = Theatre.builder()
                .name(theatreDto.name()).address(theatreDto.address())
                .city(theatreDto.city()).totalScreens(theatreDto.totalScreen())
                .activeFlag(true)
                .build();
        Theatre save = theatreRepo.save(theatre);
        return ResponseEntity.status(HttpStatus.CREATED).body("Theatre created successfully");
    }
}
