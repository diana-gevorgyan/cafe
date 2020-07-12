package com.sflpro.cafe.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(
        name = "c_order",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}, name = "c_order_id_pk"),
                @UniqueConstraint(columnNames = {"table_id"}, name = "c_order_c_table_id_fk")
        },
        indexes = {
                @Index(columnList = "table_id, status", name = "c_order_c_table_id_status_index"),
                @Index(columnList = "id", name = "c_order_id_pk"),
                @Index(columnList = "table_id", name = "c_order_c_table_id_fk")
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "table_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "c_order_c_table_id_fk")
    )
    private Table table;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(table, order.table) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, table, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", table=" + table +
                ", status=" + status +
                '}';
    }
}
