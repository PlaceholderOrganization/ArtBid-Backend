package firegruppen.artbid.api;

import firegruppen.artbid.dto.AuctionResponse;
import firegruppen.artbid.service.AuctionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/auctions")
public class AuctionController {
    AuctionService auctionService;

    public AuctionController(AuctionService auctionService){
        this.auctionService = auctionService;
    }

    @GetMapping
    List<AuctionResponse> getAuctions(){
        return auctionService.getAuctions();
    }
}
