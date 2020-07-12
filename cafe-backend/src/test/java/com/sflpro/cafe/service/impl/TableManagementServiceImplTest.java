package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.domain.Table;
import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.UserType;
import com.sflpro.cafe.domain.create.TableCreationRequest;
import com.sflpro.cafe.repository.TableRepository;
import com.sflpro.cafe.service.UserService;
import com.sflpro.cafe.service.impl.TableManagementServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class TableManagementServiceImplTest {

    private static final Random random = ThreadLocalRandom.current();

    @Mock
    private TableRepository tableRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TableManagementServiceImpl service;

    @AfterEach
    void tearDown() {
        validateMockitoUsage();
        verifyNoMoreInteractions(tableRepository, userService);
    }

    @Test
    void getAll() {
        // Given
        final User user = new User(
                random.nextLong(),
                UserType.WAITER,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        final List<Table> expectedTables = List.of(
                new Table(random.nextLong(), random.nextInt(), user),
                new Table(random.nextLong(), random.nextInt(), user),
                new Table(random.nextLong(), random.nextInt(), user)
        );

        when(tableRepository.findAll()).thenReturn(expectedTables);

        // When
        final List<Table> actualTables = service.getAll();

        // Then
        assertThat(actualTables).isNotNull().isSameAs(expectedTables);
        verify(tableRepository).findAll();
    }

    @Test
    void create() {
        // Given
        final int number = random.nextInt();
        final long waiterId = random.nextLong();
        final User user = new User(
                waiterId,
                UserType.WAITER,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );

        final TableCreationRequest creationRequest = new TableCreationRequest(number, waiterId);

        when(tableRepository.findByNumber(number)).thenReturn(Optional.empty());
        when(userService.get(waiterId)).thenReturn(user);

        final long expectedTableId = random.nextLong();
        doAnswer(invocation -> {
            final Table table = invocation.getArgument(0);

            assertNotNull(table);
            assertEquals(number, table.getNumber());
            assertEquals(user, table.getUser());

            table.setId(expectedTableId);

            return table;
        }).when(tableRepository).save(any(Table.class));

        // When
        final Table actualTable = service.create(creationRequest);
        assertNotNull(actualTable);
        assertEquals(expectedTableId, actualTable.getId());

        verify(tableRepository).findByNumber(number);
        verify(userService).get(waiterId);
        verify(tableRepository).save(any(Table.class));
    }

    @Test
    void getAllByUserId() {
        // Given
        final Long userId = random.nextLong();
        final User user = new User(
                userId,
                UserType.WAITER,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        final List<Table> expectedTables = List.of(
                new Table(random.nextLong(), random.nextInt(), user),
                new Table(random.nextLong(), random.nextInt(), user),
                new Table(random.nextLong(), random.nextInt(), user)
        );

        when(userService.get(userId)).thenReturn(user);
        when(tableRepository.findAllByUser(user)).thenReturn(expectedTables);

        // When
        final List<Table> actualTables = service.getAllByUserId(userId);

        // Then
        assertThat(actualTables)
                .isNotNull()
                .isSameAs(expectedTables);

        verify(userService).get(userId);
        verify(tableRepository).findAllByUser(user);
    }

    @Test
    void assignUserToTable() {
        // Given
        final Long userId = random.nextLong();
        final User user = new User(
                userId,
                UserType.WAITER,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        final long tableId = random.nextLong();
        final Table table = new Table(tableId, random.nextInt(), user);

        when(userService.get(userId)).thenReturn(user);
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(table));

        doAnswer(invocation -> {
            final Table inTable = invocation.getArgument(0);

            assertNotNull(inTable);
            assertEquals(user, inTable.getUser());

            return inTable;
        }).when(tableRepository).save(any(Table.class));

        // When
        final Table actualTable = service.assignUserToTable(userId, tableId);

        // Then
        assertNotNull(actualTable);

        verify(userService).get(userId);
        verify(tableRepository).findById(tableId);
        verify(tableRepository).save(any(Table.class));
    }
}