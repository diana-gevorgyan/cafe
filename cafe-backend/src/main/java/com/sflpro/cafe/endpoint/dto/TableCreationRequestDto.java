package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class TableCreationRequestDto {

    @NotNull
    @PositiveOrZero
    private Integer number;

    private Long waiterId;

    public TableCreationRequestDto() {
        this(null, null);
    }

    @JsonCreator
    public TableCreationRequestDto(
            @JsonProperty("number") final Integer number,
            @JsonProperty("waiterId") final Long waiterId
    ) {
        this.number = number;
        this.waiterId = waiterId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }
}
