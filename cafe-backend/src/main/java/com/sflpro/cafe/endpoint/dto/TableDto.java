package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TableDto {

    private Long id;
    private Integer number;
    private UserDto user;

    public TableDto() {
        this(null, null, null);
    }

    @JsonCreator
    public TableDto(
            @JsonProperty("id") final Long id,
            @JsonProperty("number") final Integer number,
            @JsonProperty("user") final UserDto user
    ) {
        this.id = id;
        this.number = number;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
