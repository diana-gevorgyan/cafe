package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sflpro.cafe.domain.UserType;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private UserType type;

    public UserDto() {
        this(null, null, null, null);
    }

    @JsonCreator
    public UserDto(
            @JsonProperty("id") final Long id,
            @JsonProperty("name") final String name,
            @JsonProperty("email") final String email,
            @JsonProperty("type") final UserType type
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
