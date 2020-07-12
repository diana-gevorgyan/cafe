package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductDto() {
        this(null, null, null);
    }

    @JsonCreator
    public ProductDto(
            @JsonProperty("id") final Long id,
            @JsonProperty("name") final String name,
            @JsonProperty("price") final BigDecimal price
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
