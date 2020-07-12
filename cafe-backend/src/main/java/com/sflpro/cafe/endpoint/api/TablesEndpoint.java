package com.sflpro.cafe.endpoint.api;

import com.sflpro.cafe.domain.Table;
import com.sflpro.cafe.domain.create.TableCreationRequest;
import com.sflpro.cafe.endpoint.dto.TableCreationRequestDto;
import com.sflpro.cafe.endpoint.dto.TableDto;
import com.sflpro.cafe.mapper.ObjectMapperUtils;
import com.sflpro.cafe.service.TableManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/tables")
public class TablesEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(TablesEndpoint.class);

    private final TableManagementService tableManagementService;

    public TablesEndpoint(final TableManagementService tableManagementService) {
        this.tableManagementService = tableManagementService;
    }

    @GetMapping("/all")
    public List<TableDto> getAll() {
        LOGGER.debug("Getting all tables");

        final List<Table> tables = tableManagementService.getAll();
        final List<TableDto> result = ObjectMapperUtils.mapAll(tables, TableDto.class);

        LOGGER.info("Done getting all tables");
        return result;
    }

    @PostMapping("/create")
    public TableDto create(@Valid @NotNull @RequestBody final TableCreationRequestDto tableCreationRequestDto) {
        LOGGER.debug("Creating table with number: {}", tableCreationRequestDto.getNumber());

        final TableCreationRequest creationRequest = ObjectMapperUtils
                .map(tableCreationRequestDto, TableCreationRequest.class);
        final Table table = tableManagementService.create(creationRequest);
        final TableDto result = ObjectMapperUtils.map(table, TableDto.class);

        LOGGER.info("Done creating table with id: {}", result.getId());
        return result;
    }

    @GetMapping("/waiter/{id}")
    public List<TableDto> getByUserId(@NotBlank @PathVariable("id") final Long id) {
        LOGGER.debug("Getting tables assigned to user:{}", id);

        final List<Table> assignedTables = tableManagementService.getAllByUserId(id);
        final List<TableDto> result = ObjectMapperUtils.mapAll(assignedTables, TableDto.class);

        LOGGER.info("Done getting tables assigned");
        return result;
    }

    @PutMapping("/{tableId}/assign/{userId}")
    public TableDto assignUserToTable(
            @NotNull @PathVariable("userId") final Long userId,
            @NotNull @PathVariable("tableId") final Long tableId
    ) {
        LOGGER.debug("Assigning user:'{}' to table:'{}", userId, tableId);

        final Table reassignedTable = tableManagementService.assignUserToTable(userId, tableId);
        final TableDto result = ObjectMapperUtils.map(reassignedTable, TableDto.class);

        LOGGER.info("Done assigning user:'{}' to table:'{}'", userId, tableId);
        return result;
    }
}
