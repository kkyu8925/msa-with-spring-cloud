package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        userDTO.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // mapper 객체로 UserDTO -> UserEntity 변환
        UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDTO.getPwd()));

        userRepository.save(userEntity);

        // mapper 객체로 UserEntity -> UserDTO 변환
        return mapper.map(userEntity, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUserId(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            throw null;
        }

        // mapper 객체로 UserEntity -> UserDTO 변환
        UserDTO userDTO = new ModelMapper().map(userEntity, UserDTO.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDTO.setOrders(orders);

        return userDTO;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

//        if(userEntity==null) {
//            throw new UsernameNotFoundException(email);
//        }

        return new ModelMapper().map(userEntity, UserDTO.class);
    }
}
