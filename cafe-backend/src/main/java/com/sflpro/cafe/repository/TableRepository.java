package com.sflpro.cafe.repository;

import com.sflpro.cafe.domain.Table;
import com.sflpro.cafe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findAllByUser(User user);

    Optional<Table> findByNumber(Integer number);
}
