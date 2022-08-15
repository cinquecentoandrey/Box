package com.cinquecento.project.BoxCompany.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public class OrderDTO {
    private LocalDateTime orderDate;

    private LocalDateTime deliveryDate;

    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 60, message = "Ship address should be between 0 and 60 symbols.")
    private String shipAddress;

    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Ship city should be between 0 and 30 symbols.")
    private String shipCity;

    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Ship postal code should be between 0 and 30 symbols.")
    private String shipPostalCode;

    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 16, message = "Country name should be between 0 and 16 symbols.")
    private String shipCountry;

    private Boolean status;

    private CustomerDTO customer;

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

}
