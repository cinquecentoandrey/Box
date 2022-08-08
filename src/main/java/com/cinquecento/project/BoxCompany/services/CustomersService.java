package com.cinquecento.project.BoxCompany.services;


import com.cinquecento.project.BoxCompany.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.BoxCompany.models.Customer;
import com.cinquecento.project.BoxCompany.models.Order;

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

    /*public List<Orders> getOrdersByCustomerId(String id) {
        Optional<Customer> customer = customersRepository.findById(id);

        if(customer.isPresent()) {
            Hibernate.initialize(customer.get().getOrders());
            return customer.get().getOrders();
        }
        else {
            return Collections.emptyList();
        }
    }*/

    @Transactional
    public void update(int id, Customer updatedCustomer) {
        customersRepository.save(updatedCustomer);
    }

    @Transactional
    public void add(Customer customer) {
        customersRepository.save(customer);
    }

    // надо подумать стоит ли вообще реализовывать этот метод
    @Transactional
    public void delete(Customer customer) {
        customersRepository.delete(customer);
    }


}
