package firegruppen.artbid.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 300)
    String description;

    @Column(nullable = false)
    int rating;


    LocalDate date;

    @ManyToOne
    @JoinColumn(name = "artwork_id", nullable = false)
    Artwork artwork;


    @ManyToOne
    @JoinColumn(name = "reviewer_username", nullable = false)
    Member member;

    public Review(String description, int rating, LocalDate date, Artwork artwork, Member member) {
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.artwork = artwork;
        artwork.addReview(this);
        this.member = member;
        member.addReview(this);
    }

    public Review(String description, int rating, LocalDate date) {
        this.description = description;
        this.rating = rating;
        this.date = date;
    }
}
