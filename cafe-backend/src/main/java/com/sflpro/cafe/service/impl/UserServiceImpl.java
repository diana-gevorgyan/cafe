package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.configuration.security.UserPrincipal;
import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.UserType;
import com.sflpro.cafe.domain.create.UserCreationRequest;
import com.sflpro.cafe.exception.ResourceAlreadyExistsException;
import com.sflpro.cafe.exception.ResourceNotFoundException;
import com.sflpro.cafe.repository.UserRepository;
import com.sflpro.cafe.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public User get(final Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> ResourceNotFoundException.createInstance(User.class, "id:" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllWaiterUsers() {
        return userRepository.findAllByType(UserType.WAITER);
    }

    @Override
    @Transactional
    public User create(final UserCreationRequest request) {
        final String email = request.getEmail();
        userRepository
                .findByEmail(email)
                .ifPresent((user) -> {
                    throw ResourceAlreadyExistsException.createInstance(User.class, "email", email);
                });

        final User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(request.getName());
        newUser.setType(request.getType());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String email) {
        final Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No user with email:%s has been found", email));
        }
        return new UserPrincipal(userOpt.get());
    }
}
