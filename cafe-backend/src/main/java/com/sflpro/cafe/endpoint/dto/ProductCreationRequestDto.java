package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProductCreationRequestDto {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    public ProductCreationRequestDto() {
        this(null, null);
    }

    @JsonCreator
    public ProductCreationRequestDto(
            @JsonProperty("name") final String name,
            @JsonProperty("price") final BigDecimal price
    ) {
        this.name = name;
        this.price = price;
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
