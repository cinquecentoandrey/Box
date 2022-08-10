package com.cinquecento.project.BoxCompany.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class OrderDetailsDTO {
    @Column(name = "box_price")
    private Double boxPrice;

    @Column(name = "quantity")
    @NotNull
    private Integer quantity;

    @Column(name = "discount")
    @NotNull
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private OrderDTO order;

    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "box_id")
    private BoxDTO box;

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

    public OrderDTO getOrders() {
        return order;
    }

    public void setOrders(OrderDTO order) {
        this.order = order;
    }

    public BoxDTO getBox() {
        return box;
    }

    public void setBox(BoxDTO box) {
        this.box = box;
    }
}
