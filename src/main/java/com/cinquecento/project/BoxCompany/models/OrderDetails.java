package com.cinquecento.project.BoxCompany.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Order_details")
public class OrderDetails implements Serializable {

    @Id
    @Column(name = "order_details_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailsId;

    @Column(name = "box_price")
    private Double boxPrice;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "discount")
    @NotNull
    private Double discount;

    @Column(name = "total")
    private Double total;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "box_id", referencedColumnName = "box_id")
    private Box box;

    public OrderDetails() {}

    public OrderDetails(Integer quantity, Double discount) {
        this.boxPrice = box.getBoxPrice(); // not NPE
        this.quantity = quantity;
        this.discount = discount;
        this.total = quantity * boxPrice * (1 - discount);
    }

    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public Double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(Double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Order getOrders() {
        return order;
    }

    public void setOrders(Order order) {
        this.order = order;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", boxPrice=" + boxPrice +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", order=" + order.getOrderId() +
                ", box=" + box.getBoxId() +
                '}';
    }
}
