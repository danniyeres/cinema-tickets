package org.example.cinematickets.repository;

import org.example.cinematickets.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}