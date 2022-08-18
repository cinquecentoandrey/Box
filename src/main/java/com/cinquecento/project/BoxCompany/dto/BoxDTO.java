package com.cinquecento.project.BoxCompany.dto;


import javax.validation.constraints.NotNull;

public class BoxDTO {

    private int boxId;
    @NotNull(message = "Box name should not be null.")
    private String boxName;

    @NotNull(message = "Box length should not be null.")
    private Integer boxLength;

    @NotNull(message = "Box width should not be null.")
    private Integer boxWidth;

    @NotNull(message = "Box height should not be null.")
    private Integer boxHeight;

    @NotNull(message = "Box price should not be null.")
    private Double boxPrice;

    private Integer boxInStock;

    private Integer boxOnOrder;

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

}
