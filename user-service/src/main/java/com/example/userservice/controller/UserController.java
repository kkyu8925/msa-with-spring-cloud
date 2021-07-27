package com.example.userservice.controller;


import com.example.userservice.dto.UserDTO;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Environment env;
    private final Greeting greeting;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage(); // @Value 사용으로 위에 줄 주석처리, 같은 코드
    }

    /**
     * 사용자 생성
     */
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // mapper 객체로 user -> UserDTO 변환
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userService.createUser(userDTO);

        ResponseUser responseUser = mapper.map(userDTO, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    /**
     * 전체 사용자 조회
     */
    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {

        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> users = new ArrayList<>();
        userList.forEach(v -> users.add(new ModelMapper().map(v, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /**
     * 사용자 조회
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {

        UserDTO userDTO = userService.getUserByUserId(userId);

        ResponseUser returnValue = new ModelMapper().map(userDTO, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}