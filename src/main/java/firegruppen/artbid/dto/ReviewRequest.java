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
    Integer rating;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate date;
    String username;
    Integer artworkId;

    public static Review getReviewEntity(ReviewRequest reviewRequest) {
        return new Review(reviewRequest.description, reviewRequest.rating, reviewRequest.date, Artwork artwork, Member member);
    }

    public ReviewRequest(Review review) {
        this.id = review.getId();
        this.description = review.getDescription();
        this.rating = review.getRating();
        this.date = review.getDate();
        this.username = review.getMember().getUsername();
        this.artworkId = review.getArtwork().getArtworkId();
    }


}