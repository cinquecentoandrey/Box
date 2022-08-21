package com.cinquecento.project.Box.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinquecento.project.Box.models.Customer;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCompanyName(String name);

}
