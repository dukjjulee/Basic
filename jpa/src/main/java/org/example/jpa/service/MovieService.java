package org.example.jpa.service;

import lombok.RequiredArgsConstructor;
import org.example.jpa.dto.MovieRequest;
import org.example.jpa.dto.MovieResponse;
import org.example.jpa.entity.Movie;
import org.example.jpa.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle()
                )).toList();
    }

    @Transactional(readOnly = true)
    public MovieResponse findOne(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없습니다.")
        );
        return new  MovieResponse(
                movie.getId(),
                movie.getTitle()
        );
    }

    @Transactional
    public MovieResponse update(Long movieId, MovieRequest request) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없습니다.")
        );

        movie.updateTitle(request.getTitle());

        return new MovieResponse(
                movie.getId(),
                movie.getTitle()
        );
    }
}
