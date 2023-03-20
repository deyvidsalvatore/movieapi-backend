package io.github.deyvidsalvatore.movieapi.services;

import io.github.deyvidsalvatore.movieapi.models.Movie;
import io.github.deyvidsalvatore.movieapi.repositories.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Movie singleMovie(ObjectId id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O filme com o ID " + id + " não foi encontrado."));
    }

    public Movie singleMovieByImdbId(String imdbId){
        return movieRepository.findByImdbId(imdbId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O filme com o IMDB ID " + imdbId + " não foi encontrado."));
    }



}
