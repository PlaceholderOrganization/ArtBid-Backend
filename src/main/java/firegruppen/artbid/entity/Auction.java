package firegruppen.artbid.entity;

import firegruppen.artbid.dto.AuctionRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "auction")
    List<Bid> bids;

    public void addBid(Bid bid) {
        if(bids==null) {
            bids = new ArrayList<>();
        }
        bids.add(bid);
    }

    public Auction(@NonNull int artworkId, @NonNull LocalDate startDate, @NonNull LocalDate endDate,
                   @NonNull double startBid, @NonNull double currentBid, @NonNull double minimumIncrement) {
        this.artworkId = artworkId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startBid = startBid;
        this.currentBid = currentBid;
        this.minimumIncrement = minimumIncrement;
    }


    public static Auction getAuctionEntity(AuctionRequest request){
        Auction auction = new Auction();
        auction.setAuctionId(request.getAuctionId());
        auction.setArtworkId(request.getArtworkId());
        auction.setStartDate(request.getStartDate());
        auction.setEndDate(request.getEndDate());
        auction.setStartBid(request.getStartBid());
        auction.setCurrentBid(request.getCurrentBid());
        auction.setMinimumIncrement(request.getMinimumIncrement());
        return auction;
    }
}
