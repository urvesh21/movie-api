package dev.urvesh.movies.reviews;

import dev.urvesh.movies.reviewReply.ReviewReply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    public Review(String reviewBody, String imdbId) {
        this.body = reviewBody;
        this.imdbId = imdbId;
    }
    @Id
    private ObjectId id;
    private String body;

    private String imdbId;

    @DocumentReference
    private List<ReviewReply> reviewReplies;

}
