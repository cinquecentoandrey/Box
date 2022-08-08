package com.cinquecento.project.BoxCompany.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id" )
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    public Order() {}

    public Order(String shipAddress, String shipCity, String shipPostalCode, String shipCountry) {
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", shipAddress='" + shipAddress + '\'' +
                ", shipCity='" + shipCity + '\'' +
                ", shipPostalCode='" + shipPostalCode + '\'' +
                ", shipCountry='" + shipCountry + '\'' +
                ", customer=" + customer +
                '}';
    }
}
