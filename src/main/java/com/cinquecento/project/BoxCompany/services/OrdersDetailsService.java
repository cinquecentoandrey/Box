package com.cinquecento.project.BoxCompany.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cinquecento.project.BoxCompany.models.Box;
import com.cinquecento.project.BoxCompany.models.Order;
import com.cinquecento.project.BoxCompany.models.OrderDetails;
import com.cinquecento.project.BoxCompany.repositories.OrderDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final BoxesService boxesService;
    private final OrdersService ordersService;

    @Autowired
    public OrdersDetailsService(OrderDetailsRepository orderDetailsRepository, BoxesService boxesService, OrdersService ordersService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.boxesService = boxesService;
        this.ordersService = ordersService;

    }

    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> findById(int id) {
        return orderDetailsRepository.findById(id);
    }

    @Transactional
    public void setCredentials(OrderDetails orderDetails, int box_id, int order_id) {
        Optional<Box> box = boxesService.findById(box_id);
        Optional<Order> order = ordersService.findById(order_id);
        if (box.isPresent() && order.isPresent()) {
            orderDetails.setBox(box.get());
            orderDetails.setOrder(order.get());
            orderDetails.setBoxPrice(box.get().getBoxPrice());
            box.get().getOrderDetails().add(orderDetails);
            order.get().getOrderDetails().add(orderDetails);
        }

    }

    @Transactional
    public void add(List<OrderDetails> orderDetails) {
        orderDetailsRepository.saveAll(orderDetails);
    }

}

