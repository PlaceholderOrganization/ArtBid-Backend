package firegruppen.artbid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import firegruppen.artbid.entity.Auction;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionResponse {

    private int auctionId;
    private int artworkId;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;
    private double startBid;
    private double currentBid;
    private double minimumIncrement;

    public AuctionResponse(Auction auction){
        this.auctionId = auction.getAuctionId();
        this.artworkId = auction.getArtworkId();
        this.startDate = auction.getStartDate();
        this.endDate = auction.getEndDate();
        this.startBid = auction.getStartBid();
        this.currentBid = auction.getCurrentBid();
        this.minimumIncrement = auction.getMinimumIncrement();
    }
}
