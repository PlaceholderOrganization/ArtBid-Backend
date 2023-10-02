package firegruppen.artbid.service;

import firegruppen.artbid.dto.AuctionResponse;
import firegruppen.artbid.entity.Auction;
import firegruppen.artbid.repository.AuctionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository){
        this.auctionRepository = auctionRepository;
    }

    public List<AuctionResponse> getAuctions(){
        List<Auction> auctions = auctionRepository.findAll();

        return auctions.stream().map((AuctionResponse::new)).toList();
    }
}
