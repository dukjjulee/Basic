package org.example.jpa2.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.dto.ReviewRequest;
import org.example.jpa.dto.ReviewResponse;
import org.example.jpa.entity.Movie;
import org.example.jpa.entity.Review;
import org.example.jpa.repository.MovieRepository;
import org.example.jpa.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없습니다")
        );
        Review review = new Review(
                request.getContents(),
                movie
        );
        Review saveReview = reviewRepository.save(review);
        return new ReviewResponse(
                saveReview.getId(),
                saveReview.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없습니다")
        );

        List<Review> movies = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();

        for (Review review : movies) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent()
                    )
            );
        }
        return dtos;
    }
}
