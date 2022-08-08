package com.cinquecento.project.BoxCompany.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class OrderDTO {
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "ship_address")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 60, message = "Ship address should be between 0 and 60 symbols.")
    private String shipAddress;

    @Column(name = "ship_city")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Ship city should be between 0 and 30 symbols.")
    private String shipCity;

    @Column(name = "ship_postal_code")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Ship postal code should be between 0 and 30 symbols.")
    private String shipPostalCode;

    @Column(name = "ship_country")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 16, message = "Country name should be between 0 and 16 symbols.")
    private String shipCountry;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
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

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
