package com.cinquecento.project.BoxCompany.dto;


import javax.validation.constraints.NotNull;

public class BoxDTO {
    @NotNull
    private String boxName;

    @NotNull
    private Integer boxLength;

    @NotNull
    private Integer boxWidth;

    @NotNull
    private Integer boxHeight;

    @NotNull
    private Double boxPrice;

    private Integer boxInStock;

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
