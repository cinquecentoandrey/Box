package com.cinquecento.project.BoxCompany.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinquecento.project.BoxCompany.models.Customer;
import com.cinquecento.project.BoxCompany.models.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCustomer(Customer customer);
}
