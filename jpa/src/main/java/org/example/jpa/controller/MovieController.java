package org.example.jpa.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa.dto.MovieRequest;
import org.example.jpa.dto.MovieResponse;
import org.example.jpa.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.save(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> findOne(
            @PathVariable Long movieId
    ){
        return ResponseEntity.ok(movieService.findOne(movieId));
    }

    @PatchMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable Long movieId,
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.update(movieId,request));
    }
}
