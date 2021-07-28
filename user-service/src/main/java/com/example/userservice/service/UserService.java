package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.jpa.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    // 사용자 생성
    UserDTO createUser(UserDTO userDTO);

    // 사용자 조회
    UserDTO getUserByUserId(String userId);

    // 모든 사용자 조회
    Iterable<UserEntity> getUserByAll();
}
