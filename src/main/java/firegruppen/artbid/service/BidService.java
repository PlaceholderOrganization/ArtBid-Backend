package firegruppen.artbid.service;

import firegruppen.artbid.dto.BidRequest;
import firegruppen.artbid.dto.BidResponse;
import firegruppen.artbid.entity.Auction;
import firegruppen.artbid.entity.Bid;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.AuctionRepository;
import firegruppen.artbid.repository.BidRepository;
import firegruppen.artbid.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BidService {

    BidRepository bidRepository;
    AuctionRepository auctionRepository;
    MemberRepository memberRepository;

    public BidService(BidRepository bidRepository, AuctionRepository auctionRepository, MemberRepository memberRepository) {
        this.bidRepository = bidRepository;
        this.auctionRepository = auctionRepository;
        this.memberRepository = memberRepository;
    }

    public List<BidResponse> getBidsByAuction(int auctionId) {
        List<Bid> bids = bidRepository.findBidsByAuction_AuctionId(auctionId);
        List<BidResponse> response = bids.stream().map(BidResponse::new).toList();
        return response;
    }

    public ResponseEntity<Boolean> addBidToAuctionSilent(BidRequest body, int auctionId) {
        Auction auction = auctionRepository.findById(auctionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auction not found"));
        Member member = memberRepository.findById(body.getUsername()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        Bid bid = BidRequest.getBidEntity(body);
        if(bid.getAmount()>auction.getStartBid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't bid under minimum");
        }
        bid.setAuction(auction);
        bid.setMember(member);
        bidRepository.save(bid);
        return ResponseEntity.ok(true);
    }
}
