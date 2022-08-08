package com.cinquecento.project.BoxCompany.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CustomerDTO {
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
    private String postal_code;

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

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
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

}

