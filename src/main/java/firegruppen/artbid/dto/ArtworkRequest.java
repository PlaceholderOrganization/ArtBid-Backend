package firegruppen.artbid.dto;

import firegruppen.artbid.entity.Artwork;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtworkRequest {

    int artworkId;
    String title;
    String category;
    String description;
    String image;
    double price;
    LocalDate uploadDate;
    boolean forSale;

    public static Artwork getArtworkEntity(ArtworkRequest art){
        return new Artwork(art.getTitle(), art.getCategory(), art.getDescription(), art.getPrice(), art.isForSale());
    }

    public ArtworkRequest(Artwork art){
        this.title = art.getTitle();
        this.category = art.getCategory();
        this.description = art.getDescription();
        this.price = art.getPrice();
        this.forSale = art.isForSale();
    }

}
