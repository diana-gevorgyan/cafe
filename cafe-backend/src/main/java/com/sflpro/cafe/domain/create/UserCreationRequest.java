package com.sflpro.cafe.domain.create;

import com.sflpro.cafe.domain.UserType;

public class UserCreationRequest {

    private String name;
    private String email;
    private UserType type;
    private String password;

    public UserCreationRequest() {
        this(null, null, null, null);
    }

    public UserCreationRequest(
            final String name,
            final String email,
            final UserType type,
            final String password
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
