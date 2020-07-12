package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.domain.Table;
import com.sflpro.cafe.domain.User;
import com.sflpro.cafe.domain.create.TableCreationRequest;
import com.sflpro.cafe.exception.ResourceAlreadyExistsException;
import com.sflpro.cafe.exception.ResourceNotFoundException;
import com.sflpro.cafe.repository.TableRepository;
import com.sflpro.cafe.service.TableManagementService;
import com.sflpro.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TableManagementServiceImpl implements TableManagementService {

    private final TableRepository tableRepository;
    private final UserService userService;

    @Autowired
    public TableManagementServiceImpl(final TableRepository tableRepository, final UserService userService) {
        this.tableRepository = tableRepository;
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    @Transactional
    public Table create(final TableCreationRequest tableCreationRequest) {
        final Integer number = tableCreationRequest.getNumber();
        final Optional<Table> tableOpt = tableRepository.findByNumber(number);
        if (tableOpt.isPresent()) {
            throw ResourceAlreadyExistsException.createInstance(Table.class, "number", number);
        }

        final User waiter = userService.get(tableCreationRequest.getWaiterId());

        final Table newTable = new Table();
        newTable.setNumber(number);
        newTable.setUser(waiter);

        return tableRepository.save(newTable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Table> getAllByUserId(final Long id) {
        final User user = userService.get(id);
        return tableRepository.findAllByUser(user);
    }

    @Override
    @Transactional
    public Table assignUserToTable(final Long userId, final Long tableId) {
        final User user = userService.get(userId);
        final Table table = tableRepository
                .findById(tableId)
                .orElseThrow(() -> ResourceNotFoundException.createInstance(Table.class, "id:" + tableId));

        table.setUser(user);

        return tableRepository.save(table);
    }
}
