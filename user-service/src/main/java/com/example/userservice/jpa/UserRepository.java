package com.example.userservice.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    // 사용자 조회
    UserEntity findByUserId(String userId);
    // 사용자 이메일 조회
    UserEntity findByEmail(String username);
}