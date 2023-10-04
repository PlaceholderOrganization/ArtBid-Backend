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
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    double amount;
    LocalDate date;

    @ManyToOne
    Auction auction;

    @ManyToOne
    @JoinColumn(name = "member_bid", nullable = false)
    Member member;


    public Bid(double amount, LocalDate date, Auction auction, Member member) {
        this.amount = amount;
        this.date = date;
        this.auction = auction;
        this.member = member;
        auction.addBid(this);
        member.addBid(this);
    }
}
