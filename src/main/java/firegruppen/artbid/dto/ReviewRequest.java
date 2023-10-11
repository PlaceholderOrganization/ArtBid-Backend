package firegruppen.artbid.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import firegruppen.artbid.entity.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {

    Integer id;
    String description;
    int rating;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate date;
    String username;
    Integer artworkId;

    public static Review getReviewEntity(ReviewRequest r) {
        return new Review(r.getDescription(), r.getRating(), r.getDate());
    }

    //Think its for tests
    public ReviewRequest(Review review) {
        this.id = review.getId();
        this.description = review.getDescription();
        this.rating = review.getRating();
        this.date = review.getDate();
        this.username = review.getMember().getUsername();
        this.artworkId = review.getArtwork().getArtworkId();
    }


}
