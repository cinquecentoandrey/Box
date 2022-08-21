package com.cinquecento.project.Box.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinquecento.project.Box.models.Customer;
import com.cinquecento.project.Box.models.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
}
