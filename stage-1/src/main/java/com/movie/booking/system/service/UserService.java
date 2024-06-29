package com.movie.booking.system.service;

import com.movie.booking.system.dto.UserDto;
import com.movie.booking.system.entity.User;
import com.movie.booking.system.repo.UserRepo;
import com.movie.booking.system.util.Role;
import com.movie.booking.system.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepo userRepo;
    public ResponseEntity<Object> getAllUser() {
        List<UserVo> collect = userRepo.findAll().stream().map(i -> new UserVo(i.getUserId(), i.getUsername(), i.getEmail(), i.getPhoneNumber(), i.getRole(), i.isActiveFlag())).collect(Collectors.toList());
        return ResponseEntity.ok(collect);
    }

    public ResponseEntity<Object> getUserById(long userId) {
        if (userRepo.existsById(userId))
        {
            User i = userRepo.findById(userId).get();
            UserVo userVo = new UserVo(i.getUserId(), i.getUsername(), i.getEmail(), i.getPhoneNumber(), i.getRole(), i.isActiveFlag());
            return ResponseEntity.ok(userVo);
        }
        else {
            throw new RuntimeException("object not found with this userId");
        }
    }

    public ResponseEntity<Object> saveUser(UserDto userDto) {
        User build = User.builder()
                .email(userDto.email()).role(Role.USER)
                .username(userDto.userName()).password(userDto.password())
                .phoneNumber(userDto.phoneNo())
                .activeFlag(true)
                .build();
        User save = userRepo.save(build);
        return ResponseEntity.status(HttpStatus.CREATED).body("user created successfully");
    }


    public ResponseEntity<Object> makeUserAdmin( long userId) {
        if (userRepo.existsById(userId))
        {
            User i = userRepo.findById(userId).get();
            i.setRole(Role.ADMIN);
            userRepo.save(i);
            return ResponseEntity.status(HttpStatus.CREATED).body("user Promoted to Admin successfully");
        }
        else {
            throw new RuntimeException("object not found with this userId");
        }
    }
}
