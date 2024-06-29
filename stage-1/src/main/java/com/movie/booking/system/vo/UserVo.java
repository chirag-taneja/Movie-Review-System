package com.movie.booking.system.vo;

import com.movie.booking.system.util.Role;

public record UserVo(Long userId, String username, String email, long phoneNumber, Role role,boolean activeFlag)  {
}
