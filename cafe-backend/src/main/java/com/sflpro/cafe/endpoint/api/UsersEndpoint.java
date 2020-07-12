package com.sflpro.cafe.endpoint.api;

import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.create.UserCreationRequest;
import com.sflpro.cafe.endpoint.dto.UserCreationRequestDto;
import com.sflpro.cafe.endpoint.dto.UserDto;
import com.sflpro.cafe.mapper.ObjectMapperUtils;
import com.sflpro.cafe.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TablesEndpoint.class);

    private final UserService userService;

    public UsersEndpoint(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserDto registerUser(@Valid @NotNull @RequestBody final UserCreationRequestDto userCreationRequestDto) {
        LOGGER.debug("Registering User with email:{}", userCreationRequestDto.getEmail());

        final UserCreationRequest request = ObjectMapperUtils.map(userCreationRequestDto, UserCreationRequest.class);
        final User createdUser = userService.create(request);
        final UserDto result = ObjectMapperUtils.map(createdUser, UserDto.class);

        LOGGER.info("Done registering user with email:{}", result.getEmail());
        return result;
    }

    @GetMapping("/waiters")
    public List<UserDto> getAllWaiterUsers() {
        LOGGER.debug("Getting all waiter users");

        final List<User> allWaiterUsers = userService.getAllWaiterUsers();
        final List<UserDto> waiters = ObjectMapperUtils.mapAll(allWaiterUsers, UserDto.class);

        LOGGER.info("Done getting all waiter users");
        return waiters;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        LOGGER.debug("Getting all users");

        final List<User> allUsers = userService.findAll();
        final List<UserDto> allUserDtos = ObjectMapperUtils.mapAll(allUsers, UserDto.class);

        LOGGER.info("Done getting all users");
        return allUserDtos;
    }
}
