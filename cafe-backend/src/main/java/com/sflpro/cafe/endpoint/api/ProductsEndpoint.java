package com.sflpro.cafe.endpoint.api;

import com.sflpro.cafe.domain.Product;
import com.sflpro.cafe.domain.create.ProductCreationRequest;
import com.sflpro.cafe.endpoint.dto.ProductCreationRequestDto;
import com.sflpro.cafe.endpoint.dto.ProductDto;
import com.sflpro.cafe.mapper.ObjectMapperUtils;
import com.sflpro.cafe.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
public class ProductsEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsEndpoint.class);

    private final ProductService productService;

    @Autowired
    public ProductsEndpoint(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        LOGGER.debug("Getting all products");

        final List<Product> allProducts = productService.getAll();
        final List<ProductDto> result = ObjectMapperUtils.mapAll(allProducts, ProductDto.class);

        LOGGER.info("Done getting all products");
        return result;
    }

    @PostMapping("/create")
    public ProductDto create(@Valid @NotNull @RequestBody final ProductCreationRequestDto creationRequestDto) {
        LOGGER.debug("Creating product");

        final ProductCreationRequest creationRequest = ObjectMapperUtils.map(creationRequestDto, ProductCreationRequest.class);
        final Product createdProduct = productService.create(creationRequest);
        final ProductDto result = ObjectMapperUtils.map(createdProduct, ProductDto.class);

        LOGGER.info("Done creating product");
        return result;
    }
}
