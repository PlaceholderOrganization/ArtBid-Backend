package firegruppen.artbid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import firegruppen.artbid.entity.Auction;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionRequest {

    private int auctionId;

    private int artworkId;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;

    private double startBid;

    private double currentBid;

    private double minimumIncrement;

    public AuctionRequest(Auction a){
        this.auctionId = a.getAuctionId();
        this.artworkId = a.getArtworkId();
        this.startDate = a.getStartDate();
        this.endDate = a.getEndDate();
        this.startBid = a.getStartBid();
        this.currentBid = a.getCurrentBid();
        this.minimumIncrement = a.getMinimumIncrement();
    }

}
