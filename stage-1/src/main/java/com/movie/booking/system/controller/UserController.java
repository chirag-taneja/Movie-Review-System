package com.movie.booking.system.controller;

import com.movie.booking.system.dto.UserDto;
import com.movie.booking.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("-id/{id}")
    public ResponseEntity<Object> getUserBYId(@PathVariable(name = "id") long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto)
    {
        return userService.saveUser(userDto);
    }

    @PutMapping("/user/admin/{id}")
    public ResponseEntity<Object> makeUserAdmin(@PathVariable(name = "id") long userId)
    {
        return userService.makeUserAdmin(userId);
    }
}
