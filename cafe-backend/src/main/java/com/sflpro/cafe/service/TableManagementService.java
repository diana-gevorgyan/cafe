package com.sflpro.cafe.service;

import com.sflpro.cafe.domain.Table;
import com.sflpro.cafe.domain.create.TableCreationRequest;

import java.util.List;

public interface TableManagementService {

    List<Table> getAll();

    Table create(TableCreationRequest tableCreationRequest);

    List<Table> getAllByUserId(Long id);

    Table assignUserToTable(Long userId, Long tableId);
}
