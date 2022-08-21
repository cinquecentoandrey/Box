package com.cinquecento.project.Box.dto;


import javax.validation.constraints.NotNull;

public class OrderDetailsDTO {

    private Integer boxId;

    private Double boxPrice;

    @NotNull(message = "Quantity should not be null.")
    private Integer quantity;

    @NotNull(message = "Discount should not be null.")
    private Double discount;

    private Double total;
    private OrderDTO order;
    private BoxDTO box;

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public BoxDTO getBox() {
        return box;
    }

    public void setBox(BoxDTO box) {
        this.box = box;
    }
}
