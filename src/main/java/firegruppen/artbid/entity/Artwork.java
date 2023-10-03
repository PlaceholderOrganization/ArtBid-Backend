package firegruppen.artbid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int artworkId;
    @Column(name = "artistId")
    int artistId; //sec key
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "category", length = 100)
    private String category; //burde category være en tabel for sig selv eller enum/ superclass??
    @Column(name = "description", length = 1000)
    private String description;
    //Billeder af art??
    @Column(name = "price")
    private double price;
    @Column(name = "UploadDate")
    private LocalDate uploadDate;
    @Column(name = "isAvailable")
    private boolean isAvailable;

    @OneToMany(mappedBy = "artwork")
    List<Review> reviews;

    public void addReview(Review review) {
        if(reviews==null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public Artwork(String title, String category, String description, double price, LocalDate uploadDate, boolean isAvailable) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.uploadDate = uploadDate;
        this.isAvailable = isAvailable;
    }

    //Superclass til dato for oprettelse/redigering??


}
