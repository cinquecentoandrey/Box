package com.cinquecento.project.Box.repositories;


import com.cinquecento.project.Box.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
