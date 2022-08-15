package com.cinquecento.project.BoxCompany.repositories;


import com.cinquecento.project.BoxCompany.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
