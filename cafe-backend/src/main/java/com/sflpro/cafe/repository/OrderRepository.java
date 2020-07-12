package com.sflpro.cafe.repository;

import com.sflpro.cafe.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
