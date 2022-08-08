package com.cinquecento.project.BoxCompany.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class BoxDTO {
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


}
