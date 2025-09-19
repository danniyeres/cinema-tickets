package org.example.cinematickets.repository;

import org.example.cinematickets.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository <Movie, Long> {
    Movie findByTitle(String title);
}
