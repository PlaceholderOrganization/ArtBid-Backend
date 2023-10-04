package firegruppen.artbid.repository;

import firegruppen.artbid.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Integer> {

    List<Bid> findBidsByAuction_AuctionId(Integer auctionId);
}
