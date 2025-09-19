package org.example.cinematickets.repository;

import org.example.cinematickets.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByMovieTitle(String title);
    Session findByHallName(String name);
    List<Session> findByHallId(Long hallId);
    List<Session> findByMovieId(Long movieId);
}