package com.sflpro.cafe.service;

import com.sflpro.cafe.domain.Product;
import com.sflpro.cafe.domain.create.ProductCreationRequest;

import java.util.List;

public interface ProductService {

    Product create(ProductCreationRequest creationRequest);

    List<Product> getAll();
}
