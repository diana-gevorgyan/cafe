package com.sflpro.cafe.domain;

import javax.persistence.Table;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(
        name = "c_product",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}, name = "c_product_id_pk"),
                @UniqueConstraint(columnNames = {"name"}, name = "c_product_name_uc")
        },
        indexes = {
                @Index(columnList = "id", name = "c_product_id_pk", unique = true),
                @Index(columnList = "name", name = "c_product_name_uc", unique = true)
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false, insertable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Product() {
        this(null, null, null);
    }

    public Product(final Long id, final String name, final BigDecimal price) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
