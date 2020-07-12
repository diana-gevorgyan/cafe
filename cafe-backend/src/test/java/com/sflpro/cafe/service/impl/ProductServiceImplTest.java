package com.sflpro.cafe.service.impl;

import com.sflpro.cafe.domain.Product;
import com.sflpro.cafe.domain.create.ProductCreationRequest;
import com.sflpro.cafe.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
class ProductServiceImplTest {

    private static final Random random = ThreadLocalRandom.current();

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl service;

    @AfterEach
    void tearDown() {
        validateMockitoUsage();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void create() {
        // Given
        final ProductCreationRequest productCreationRequest = new ProductCreationRequest();
        final String name = UUID.randomUUID().toString();
        productCreationRequest.setName(name);
        final BigDecimal price = new BigDecimal("1.23");
        productCreationRequest.setPrice(price);

        when(productRepository.findByName(name)).thenReturn(Optional.empty());

        final long expectedId = 22L;
        doAnswer(invocation -> {
            final Product product = invocation.getArgument(0);

            assertNotNull(product);
            assertEquals(name, product.getName());
            assertEquals(price, product.getPrice());

            product.setId(expectedId);
            return product;
        }).when(productRepository).save(any(Product.class));

        // When
        final Product actualProduct = service.create(productCreationRequest);

        // Then
        assertNotNull(actualProduct);
        assertEquals(expectedId, actualProduct.getId());

        verify(productRepository).findByName(name);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void getAll() {
        // Given
        final List<Product> expectedProduct = List.of(
                new Product(random.nextLong(), UUID.randomUUID().toString(), BigDecimal.valueOf(random.nextDouble())),
                new Product(random.nextLong(), UUID.randomUUID().toString(), BigDecimal.valueOf(random.nextDouble())),
                new Product(random.nextLong(), UUID.randomUUID().toString(), BigDecimal.valueOf(random.nextDouble()))
        );

        when(productRepository.findAll()).thenReturn(expectedProduct);

        // When
        final List<Product> actualProducts = service.getAll();

        // Then
        assertThat(actualProducts).isNotNull().isSameAs(expectedProduct);
        verify(productRepository).findAll();
    }
}