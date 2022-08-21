package com.cinquecento.project.Box.util.validators;


import com.cinquecento.project.Box.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.cinquecento.project.Box.models.Customer;

@Component
public class CustomerValidator implements Validator {

    private final CustomersService customersService;

    @Autowired
    public CustomerValidator(CustomersService customersService) {
        this.customersService = customersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        if(customersService.findByCompanyName(customer.getCompanyName()).isPresent()) {
            errors.rejectValue("companyName", "", "There is already a company with that name.");
        }

    }
}
