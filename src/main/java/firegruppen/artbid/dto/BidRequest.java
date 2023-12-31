package firegruppen.artbid.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import firegruppen.artbid.entity.Bid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidRequest {

    int id;
    double amount;
    @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
    LocalDate date;
    int auctionId;
    String username;

    public static Bid getBidEntity(BidRequest r) {
        return new Bid(r.getAmount(), r.getDate());
    }

    public BidRequest(Bid bid) {
        this.id = bid.getId();
        this.amount = bid.getAmount();
        this.date = bid.getDate();
        this.auctionId = bid.getAuction().getAuctionId();
        this.username = bid.getMember().getUsername();
    }

}
