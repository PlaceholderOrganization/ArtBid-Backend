package firegruppen.artbid.entity;

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
    @Column(name = "UploadDate")
    @CreationTimestamp
    private LocalDate uploadDate;

    @Column(name = "forSale")
    private boolean forSale;

    @Lob
    @Column(name = "image", length = 10485760)
    private String image;

   //Superclass til dato for oprettelse/redigering??

//    @ElementCollection
//    @CollectionTable(name = "artwork_images", joinColumns = @JoinColumn(name = "artwork_id"))
//    @Column(name = "image", length = 10485760)
//    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "artist")
    Member member;

//    @OneToMany(mappedBy = "artwork")
//    List<ArtworkImages> artworkImagesList;
  
   @OneToMany(orphanRemoval = true, mappedBy = "artwork")
    List<Review> reviews;

    public void addReview(Review review) {
        if(reviews==null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

//    public void addArtworkImage(ArtworkImages artworkImages) {
//        if (artworkImagesList==null) {
//            artworkImagesList = new ArrayList<>();
//        }
//        artworkImagesList.add(artworkImages);
//    }

    public Artwork(String title, String category, String description, boolean forSale, String image, Member member) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.forSale = forSale;
        this.image = image;
        this.member = member;
        member.addArtwork(this);
    }

    public Artwork(String title, String category, String description, boolean forSale, String image) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.forSale = forSale;
        this.image = image;


    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
}
