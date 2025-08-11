package org.example.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa.dto.ReviewRequest;
import org.example.jpa.dto.ReviewResponse;
import org.example.jpa.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> saveReview(
            @RequestBody ReviewRequest request,
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>>getAllReviews(
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(reviewService.findAll(movieId));
    }
}
