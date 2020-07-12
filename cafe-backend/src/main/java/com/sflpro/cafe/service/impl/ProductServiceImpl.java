package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.domain.Product;
import com.sflpro.cafe.domain.create.ProductCreationRequest;
import com.sflpro.cafe.exception.ResourceAlreadyExistsException;
import com.sflpro.cafe.repository.ProductRepository;
import com.sflpro.cafe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product create(final ProductCreationRequest creationRequest) {
        final String name = creationRequest.getName();
        final Optional<Product> productOpt = productRepository.findByName(name);
        if (productOpt.isPresent()) {
            throw ResourceAlreadyExistsException.createInstance(Product.class, "name", name);
        }

        final Product product = new Product();
        product.setName(name);
        product.setPrice(creationRequest.getPrice());

        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
