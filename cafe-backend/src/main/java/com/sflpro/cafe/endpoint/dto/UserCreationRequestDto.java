package com.sflpro.cafe.endpoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sflpro.cafe.domain.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCreationRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private UserType type;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    public UserCreationRequestDto() {
        this(null, null, null, null);
    }

    @JsonCreator
    public UserCreationRequestDto(
            @JsonProperty("name") final String name,
            @JsonProperty("email") final String email,
            @JsonProperty("type") final UserType type,
            @JsonProperty("password") final String password
    ) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
