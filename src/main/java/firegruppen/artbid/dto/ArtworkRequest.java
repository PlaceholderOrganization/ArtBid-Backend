package firegruppen.artbid.dto;

import firegruppen.artbid.entity.Artwork;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    List<String> images;
    String username;

    public static Artwork getArtworkEntity(ArtworkRequest art){
        return new Artwork(art.getTitle(), art.getCategory(), art.getDescription(), art.getPrice(), art.isForSale(), art.getImages());
    }

    public ArtworkRequest(Artwork art){
        this.artworkId = art.getArtworkId();
        this.title = art.getTitle();
        this.category = art.getCategory();
        this.description = art.getDescription();
        this.price = art.getPrice();
        this.uploadDate = art.getUploadDate();
        this.forSale = art.isForSale();
        this.image = String.join(",", art.getImages()); //Lidt trouble her med response fra postman, bummelum.
        this.username = art.getMember().getUsername();
    }

}
