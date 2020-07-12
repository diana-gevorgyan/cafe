package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.configuration.security.UserPrincipal;
import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.UserType;
import com.sflpro.cafe.domain.create.UserCreationRequest;
import com.sflpro.cafe.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Random random = ThreadLocalRandom.current();

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    @AfterEach
    void tearDown() {
        validateMockitoUsage();
        verifyNoMoreInteractions(userRepository, passwordEncoder);
    }

    @Test
    void get() {
        // Given
        final long id = random.nextLong();
        final User expectedUser = new User(
                id,
                UserType.MANAGER,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // When
        final User actualUser = service.get(id);

        // Then
        assertThat(actualUser).isNotNull().isSameAs(expectedUser);
        verify(userRepository).findById(id);
    }

    @Test
    void getAllWaiterUsers() {
        // Given
        final List<User> expectedUsers = List.of(
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        when(userRepository.findAllByType(UserType.WAITER)).thenReturn(expectedUsers);

        // When
        final List<User> actualUsers = service.getAllWaiterUsers();

        // Then
        assertThat(actualUsers).isNotNull().isSameAs(expectedUsers);
        verify(userRepository).findAllByType(UserType.WAITER);
    }

    @Test
    void create() {
        // Given
        final String name = UUID.randomUUID().toString();
        final String email = UUID.randomUUID().toString();
        final UserType type = UserType.WAITER;
        final String plainPassword = UUID.randomUUID().toString();
        final UserCreationRequest creationRequest = new UserCreationRequest(name, email, type, plainPassword);

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        final String encodedPassword = UUID.randomUUID().toString();
        when(passwordEncoder.encode(plainPassword)).thenReturn(encodedPassword);

        final long expectedId = random.nextLong();
        doAnswer(invocation -> {
            final User user = invocation.getArgument(0);

            assertNotNull(user);
            assertEquals(name, user.getName());
            assertEquals(email, user.getEmail());
            assertEquals(type, user.getType());
            assertEquals(encodedPassword, user.getPassword());

            user.setId(expectedId);

            return user;
        }).when(userRepository).save(any(User.class));

        // When
        final User createdUser = service.create(creationRequest);

        // Then
        assertNotNull(createdUser);
        assertEquals(expectedId, createdUser.getId());

        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).encode(plainPassword);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void findAll() {
        // Given
        final List<User> expectedUsers = List.of(
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString()),
                new User(random.nextLong(), UserType.WAITER, UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString())
        );

        when(userRepository.findAll()).thenReturn(expectedUsers);

        // When
        final List<User> actualUsers = service.findAll();

        // Then
        assertThat(actualUsers).isNotNull().isSameAs(expectedUsers);
        verify(userRepository).findAll();
    }

    @Test
    void loadUserByUsername() {
        // Given
        final String email = UUID.randomUUID().toString();
        final User expectedUser = new User(
                random.nextLong(),
                UserType.MANAGER,
                UUID.randomUUID().toString(),
                email,
                UUID.randomUUID().toString()
        );

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(expectedUser));

        // When
        final UserDetails actualUserDetails = service.loadUserByUsername(email);

        // Then
        assertThat(actualUserDetails).isNotNull().isInstanceOf(UserPrincipal.class);
        final UserPrincipal userPrincipal = (UserPrincipal) actualUserDetails;
        assertThat(userPrincipal.getUser()).isNotNull().isSameAs(expectedUser);

        verify(userRepository).findByEmail(email);
    }
}
