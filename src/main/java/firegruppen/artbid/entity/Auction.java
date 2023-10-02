package firegruppen.artbid.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int auctionId;

    @NonNull
    private int artworkId;

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    @NonNull
    private double startBid;

    private double currentBid;

    @NonNull
    private double minimumIncrement;

    public Auction(@NonNull int artworkId, @NonNull LocalDate startDate, @NonNull LocalDate endDate,
                   @NonNull double startBid, @NonNull double currentBid, @NonNull double minimumIncrement) {
        this.artworkId = artworkId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startBid = startBid;
        this.currentBid = currentBid;
        this.minimumIncrement = minimumIncrement;
    }


    /*
StartDate: Start date and time of the auction.
EndDate: End date and time of the auction.
CurrentBid: The current highest bid for the artwork.
Other auction-related information (e.g., minimum bid increment).
*/
}
