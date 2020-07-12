package com.sflpro.cafe.domain;

import javax.persistence.Table;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(
        name = "c_user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}, name = "c_user_id_pk"),
                @UniqueConstraint(columnNames = {"email"}, name = "c_user_email_uc")
        },
        indexes = {
                @Index(columnList = "id", name = "c_user_id_pk", unique = true),
                @Index(columnList = "email", name = "c_user_email_uc", unique = true)
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, insertable = false, nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User() {
        this(null, null, null, null, null);
    }

    public User(
            final Long id,
            final UserType type,
            final String name,
            final String email,
            final String password
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                type == user.type &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
