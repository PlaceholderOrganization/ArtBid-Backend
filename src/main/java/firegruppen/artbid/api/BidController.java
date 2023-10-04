package firegruppen.artbid.api;

import firegruppen.artbid.dto.BidRequest;
import firegruppen.artbid.dto.BidResponse;
import firegruppen.artbid.service.BidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/bid")
public class BidController {

    BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @GetMapping("/{auctionId}")
    public List<BidResponse> getBidsByAuction(@PathVariable int auctionId) {
        List<BidResponse> response = bidService.getBidsByAuction(auctionId);
        return response;
    }

    @PostMapping("/{auctionId}")
    public ResponseEntity<Boolean> addBidToAuctionSilent(@RequestBody BidRequest body, @PathVariable int auctionId) {
        ResponseEntity<Boolean> response = bidService.addBidToAuctionSilent(body,auctionId);
        return response;
    }

}
