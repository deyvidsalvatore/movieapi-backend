package io.github.deyvidsalvatore.movieapi.services;

import io.github.deyvidsalvatore.movieapi.models.Movie;
import io.github.deyvidsalvatore.movieapi.models.Review;
import io.github.deyvidsalvatore.movieapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    // Cria uma nova avaliação para um filme e retorna a avaliação criada
    public Review createReview(String reviewBody, String imdbId){

        // Insere a nova avaliação no repositório de avaliações
        Review review = reviewRepository.insert(new Review(reviewBody));

        // Atualiza o documento do filme correspondente no MongoDB,
        // adicionando o ID da avaliação recém-criada à lista de IDs de avaliações associadas ao filme
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId)) // Seleciona o filme com o ID do IMDb especificado
                .apply(new Update().push("reviewIds").value(review)) // Adiciona o ID da nova avaliação à lista de IDs de avaliações do filme
                .first();

        // Retorna a avaliação criada
        return review;
    }

}
