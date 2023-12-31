package firegruppen.artbid.service;

import firegruppen.artbid.dto.AuctionRequest;
import firegruppen.artbid.dto.AuctionResponse;
import firegruppen.artbid.entity.Auction;
import firegruppen.artbid.repository.AuctionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public AuctionResponse findById(int id){
        Auction auction = getAuctionById(id);
        return new AuctionResponse(auction);
    }

    public AuctionResponse addAuction(AuctionRequest body) {
        if (auctionRepository.existsById(body.getAuctionId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This auction already exists");
        }
        Auction newAuction = Auction.getAuctionEntity(body);
        newAuction = auctionRepository.save(newAuction);
        return new AuctionResponse(newAuction);
    }

    public AuctionResponse editAuction(AuctionRequest body, int id) {

        if(id != body.getAuctionId()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change the ID of auction");
        }

        Auction auction = Auction.getAuctionEntity(body);
        /*
        auction.setAuctionId(body.getAuctionId());
        auction.setArtworkId(body.getArtworkId());
        auction.setStartDate(body.getStartDate());
        auction.setEndDate(body.getEndDate());
        auction.setStartBid(body.getStartBid());
        auction.setCurrentBid(body.getCurrentBid());
        auction.setMinimumIncrement(body.getMinimumIncrement());
        */
        auctionRepository.save(auction);
        return new AuctionResponse(auction);
    }

    public void deleteAuction(int id) {
        Auction auction = getAuctionById(id);
        auctionRepository.delete(auction);
    }

    private Auction getAuctionById(int id){
        return auctionRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Auction with this ID does not exist"));
    }

}
