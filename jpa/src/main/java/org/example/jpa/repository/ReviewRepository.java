package org.example.jpa.repository;

import org.example.jpa.entity.Movie;
import org.example.jpa.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);

}
