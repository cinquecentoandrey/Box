package com.cinquecento.project.Box.services;


import com.cinquecento.project.Box.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.Box.models.Customer;
import com.cinquecento.project.Box.models.Order;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CustomersService {

    private final CustomersRepository customersRepository;
    private final OrdersService ordersService;

    @Autowired
    public CustomersService(CustomersRepository customersRepository, OrdersService ordersService) {
        this.customersRepository = customersRepository;
        this.ordersService = ordersService;

    }

    public List<Customer> findAll() {
        return customersRepository.findAll();
    }

    public Optional<Customer> findById(int id) {
        return customersRepository.findById(id);
    }

    public Optional<Customer> findByCompanyName(String name) {
        return customersRepository.findByCompanyName(name);
    }

    public List<Order> findOrdersByCustomerId(int id) {
        Optional<Customer> customer = customersRepository.findById(id);
        if(customer.isPresent()) {
            return ordersService.findByCustomer(customer.get());
        }
        else {
            return Collections.emptyList();
        }
    }

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        updatedCustomer.setCustomerId(id);
        customersRepository.save(updatedCustomer);
    }

    @Transactional
    public void add(Customer customer) {
        customersRepository.save(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        customersRepository.delete(customer);
    }

}
