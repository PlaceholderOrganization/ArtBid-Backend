package firegruppen.artbid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(name = "artworkId")
    int artworkId;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "category", length = 100)
    private String category; 
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "UploadDate")
    @CreationTimestamp
    private LocalDate uploadDate;

    @Column(name = "forSale")
    private boolean forSale;


   //Superclass til dato for oprettelse/redigering??

    @ElementCollection
    @CollectionTable(name = "artwork_images", joinColumns = @JoinColumn(name = "artworkId"))
    @Column(name = "image", length = 10485760)
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "artist")
    Member member;

//    @OneToMany(mappedBy = "artwork")
//    List<ArtworkImages> artworkImages;
  
   @OneToMany(mappedBy = "artwork")
    List<Review> reviews;

    public void addReview(Review review) {
        if(reviews==null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public Artwork(String title, String category, String description, double price, boolean forSale, List<String> images, Member member) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.forSale = forSale;
        this.images = images;
        this.member = member;
        member.addArtwork(this);
    }

    public Artwork(String title, String category, String description, double price, boolean forSale, List<String> images) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.forSale = forSale;
        this.images = images;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
}
