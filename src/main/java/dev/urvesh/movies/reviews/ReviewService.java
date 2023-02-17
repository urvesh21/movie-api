package dev.urvesh.movies.reviews;

import dev.urvesh.movies.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = reviewRepository.insert(new Review(reviewBody, imdbId));

        mongoTemplate.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value((review))).first();

        return review;
    }

    public Optional<List<Review>> getAllReview(String imdbId) {
        return reviewRepository.findAllReviewByImdbId(imdbId);
    }
}
