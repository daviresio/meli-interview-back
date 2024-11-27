package com.meli.interview.back.subscription_api.user.resource;

import com.meli.interview.back.subscription_api.user.entity.UserEntity;
import com.meli.interview.back.subscription_api.user.resource.dto.CreateUserDTO;
import com.meli.interview.back.subscription_api.user.resource.dto.UserDTO;
import com.meli.interview.back.subscription_api.user.resource.mapper.UserMapper;
import com.meli.interview.back.subscription_api.user.dao.UserDAO;
import com.meli.interview.back.subscription_api.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/users")
public class UserPublicResource {

    private static final Logger logger = LoggerFactory.getLogger(UserPublicResource.class);

    private final UserService userService;
    private final UserMapper userMapper;

    public UserPublicResource(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        logger.info("Received request to create user with email: {}", createUserDTO.getEmail());

        UserDAO userDAO = userMapper.map(createUserDTO);

        UserEntity createdUser = userService.createUser(userDAO);

        UserDTO response = userMapper.map(createdUser);

        logger.info("User created successfully with ID: {}", createdUser.getId());
        return ResponseEntity.ok(response);

    }

}
