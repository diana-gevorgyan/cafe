package com.sflpro.cafe.service;

import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.create.UserCreationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User get(Long userId);

    List<User> getAllWaiterUsers();

    User create(UserCreationRequest request);

    List<User> findAll();
}
