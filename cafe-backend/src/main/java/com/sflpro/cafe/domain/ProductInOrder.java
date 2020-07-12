package com.sflpro.cafe.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "c_product_in_order")
public class ProductInOrder {

    @EmbeddedId
    private ProductInOrderId id;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public ProductInOrderId getId() {
        return id;
    }

    public void setId(ProductInOrderId id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
