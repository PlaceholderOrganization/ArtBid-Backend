//package firegruppen.artbid.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//
//@Entity
//public class ArtworkImages {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "imageId")
//    int imageId;
//
//    @Lob
//    @Column(name = "image", length = 10485760)//Max length juster evt ned!
//    private String image;
//
//    @ManyToOne
//    Artwork artwork;
//
//    public ArtworkImages(String image, Artwork artwork) {
//        this.image = image;
//        this.artwork = artwork;
//    }
//}
