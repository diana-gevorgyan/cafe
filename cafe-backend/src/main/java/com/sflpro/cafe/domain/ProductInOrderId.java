package com.sflpro.cafe.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductInOrderId implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "c_product_in_order_product_id_fk")
    )
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "c_product_in_order_order_id_fk")
    )
    private Order order;

    public ProductInOrderId() {
        this(null, null);
    }

    public ProductInOrderId(final Product product, final Order order) {
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderId that = (ProductInOrderId) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, order);
    }

    @Override
    public String toString() {
        return "ProductInOrderId{" +
                "product=" + product +
                ", order=" + order +
                '}';
    }
}
