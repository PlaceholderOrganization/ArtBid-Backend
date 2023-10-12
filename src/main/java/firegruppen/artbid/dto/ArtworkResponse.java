package firegruppen.artbid.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import firegruppen.artbid.entity.Artwork;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtworkResponse {

    int artworkId;
    String title;
    String category;
    String description;
    double price;
    LocalDate uploadDate;
    boolean forSale;
    String image;
    String username;
    List<ReviewResponse> reviews;

    public ArtworkResponse(Artwork art) {
        this.artworkId = art.getArtworkId();
        this.title = art.getTitle();
        this.category = art.getCategory();
        this.description = art.getDescription();
        this.price = art.getPrice();
        this.uploadDate = art.getUploadDate();
        this.forSale = art.isForSale();
        this.image = art.getImage();
        this.username = art.getMember().getUsername();
        if(art.getReviews() != null) {
            this.reviews = art.getReviews().stream().map(ReviewResponse::new).toList();
        }
    }
}
