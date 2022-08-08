package com.cinquecento.project.BoxCompany.services;


import com.cinquecento.project.BoxCompany.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.BoxCompany.models.Customer;
import com.cinquecento.project.BoxCompany.models.Order;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public Optional<Order> findById(int id) {
        return ordersRepository.findById(id);
    }

    public List<Order> findByCustomer(Customer customer) {
        List<Order> orders = customer.getOrder();
        if (orders.isEmpty()) {
            return Collections.emptyList();
        }
        return ordersRepository.findByCustomer(customer);
    }

    @Transactional
    public void add(Order order) {
        order.setOrderDate(LocalDateTime.now());
        ordersRepository.save(order);
    }

    @Transactional
    public void delivered(Order order) {
        order.setDeliveryDate(LocalDateTime.now());
        ordersRepository.save(order);
    }
    // danger!
    @Transactional
    public void delete(Order order) {
        ordersRepository.save(order);
    }
}
