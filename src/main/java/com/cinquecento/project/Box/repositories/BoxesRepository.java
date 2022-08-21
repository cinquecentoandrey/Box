package com.cinquecento.project.Box.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cinquecento.project.Box.models.Box;

import java.util.Optional;

@Repository
public interface BoxesRepository extends JpaRepository<Box, Integer> {
    Optional<Box> findByBoxName(String name);
}
