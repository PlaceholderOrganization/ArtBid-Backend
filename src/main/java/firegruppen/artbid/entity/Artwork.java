package firegruppen.artbid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    //Superclass til dato for oprettelse/redigering??


}
