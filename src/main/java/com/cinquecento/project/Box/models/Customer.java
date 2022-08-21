package com.cinquecento.project.Box.models;



import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "company_name")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 40, message = "Name should be between 0 and 40 symbols.")
    private String companyName;

    @Column(name = "contact_name")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 40, message = "Name should be between 1 and 40 symbols.")
    private String contactName;

    @Column(name = "contact_title")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Title should be between 1 and 30 symbols.")
    private String contactTitle;

    @Column(name = "address")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Address should be between 0 and 40 symbols.")
    private String address;

    @Column(name = "city")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "City name should be between 0 and 40 symbols.")
    private String city;

    @Column(name = "postal_code")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Postal code should be between 0 and 10 symbols.")
    private String postalCode;

    @Column(name = "email")
    @NotEmpty(message = "Field should not be empty.")
    @Email
    private String email;

    @Column(name = "country")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 20, message = "Address should be between 0 and 20 symbols.")
    private String country;

    @Column(name = "phone")
    @NotEmpty(message = "Field should not be empty.")
    @Size(min = 0, max = 30, message = "Phone number should be between 0 and 20 symbols.")
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> order;

    public Customer() {}

    public Customer(String companyName, String contactName, String contactTitle, String address, String city, String postalCode, String email, String country, String phone) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.country = country;
        this.phone = phone;
    }

    public Customer(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> orders) {
        this.order = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
