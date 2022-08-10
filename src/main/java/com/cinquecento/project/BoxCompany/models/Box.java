package com.cinquecento.project.BoxCompany.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Box")
public class Box {

    @Id
    @Column(name = "box_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boxId;

    @Column(name = "box_name")
    @NotNull
    private String boxName;

    @Column(name = "box_length")
    @NotNull
    private Integer boxLength;

    @Column(name = "box_width")
    @NotNull
    private Integer boxWidth;

    @Column(name = "box_height")
    @NotNull
    private Integer boxHeight;

    @Column(name = "box_price")
    @NotNull
    private Double boxPrice;

    @Column(name = "box_in_stock")
    private Integer boxInStock;

    @Column(name = "box_on_order")
    private Integer boxOnOrder;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    public Box() {}

    public Box(String boxName, Integer boxLength, Integer boxWidth, Integer boxHeight, Double boxPrice) {
        this.boxName = boxName;
        this.boxLength = boxLength;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;
        this.boxPrice = boxPrice;
    }

    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public Integer getBoxLength() {
        return boxLength;
    }

    public void setBoxLength(Integer boxLength) {
        this.boxLength = boxLength;
    }

    public Integer getBoxWidth() {
        return boxWidth;
    }

    public void setBoxWidth(Integer boxWidth) {
        this.boxWidth = boxWidth;
    }

    public Integer getBoxHeight() {
        return boxHeight;
    }

    public void setBoxHeight(Integer boxHeight) {
        this.boxHeight = boxHeight;
    }

    public Double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(Double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public Integer getBoxInStock() {
        return boxInStock;
    }

    public void setBoxInStock(Integer boxInStock) {
        this.boxInStock = boxInStock;
    }

    public Integer getBoxOnOrder() {
        return boxOnOrder;
    }

    public void setBoxOnOrder(Integer boxOnOrder) {
        this.boxOnOrder = boxOnOrder;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Box{" +
                "boxId=" + boxId +
                ", boxName='" + boxName + '\'' +
                ", boxLength=" + boxLength +
                ", boxWidth=" + boxWidth +
                ", boxHeight=" + boxHeight +
                ", boxPrice=" + boxPrice +
                ", boxInStock=" + boxInStock +
                ", boxOnOrder=" + boxOnOrder +
                '}';
    }
}
