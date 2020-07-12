package com.sflpro.cafe.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(
        name = "c_table",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}, name = "c_table_id_pkey"),
                @UniqueConstraint(columnNames = {"number"}, name = "c_table_number_uc")
        },
        indexes = {
                @Index(columnList = "id", name = "c_table_id_pkey", unique = true),
                @Index(columnList = "number", name = "c_table_number_uc", unique = true),
                @Index(columnList = "assigned_waiter_id", name = "c_table_assigned_waiter_id_index")
        }
)
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "assigned_waiter_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "c_table_assigned_waiter_id_fk")
    )
    private User user;

    public Table() {
        this(null, null, null);
    }

    public Table(final Long id, final Integer number, final User user) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) &&
                Objects.equals(number, table.number) &&
                Objects.equals(user, table.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, user);
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", number=" + number +
                ", user=" + user +
                '}';
    }
}
