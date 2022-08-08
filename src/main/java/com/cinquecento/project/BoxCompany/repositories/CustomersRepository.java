package com.cinquecento.project.BoxCompany.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinquecento.project.BoxCompany.models.Customer;

import java.util.Optional;

@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCompanyName(String name);

}
