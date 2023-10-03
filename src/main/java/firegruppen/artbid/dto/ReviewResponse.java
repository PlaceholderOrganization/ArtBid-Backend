package firegruppen.artbid.dto;

import firegruppen.artbid.entity.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    Integer id;
    String description;
    Integer rating;
    LocalDate date;
    Integer artworkId;
    String username;


    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.description = review.getDescription();
        this.rating = review.getRating();
        this.date = review.getDate();
        this.artworkId = review.getArtwork().getArtworkId();
        this.username = review.getMember().getUsername();
    }
}
