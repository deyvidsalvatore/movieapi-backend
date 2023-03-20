package io.github.deyvidsalvatore.movieapi.repositories;

import io.github.deyvidsalvatore.movieapi.models.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
}
