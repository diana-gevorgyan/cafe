package com.sflpro.cafe.repository;

import com.sflpro.cafe.domain.ProductInOrder;
import com.sflpro.cafe.domain.ProductInOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrderId> {
}
