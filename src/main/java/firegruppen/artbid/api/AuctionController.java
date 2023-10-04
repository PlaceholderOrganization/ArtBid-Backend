package firegruppen.artbid.api;

import firegruppen.artbid.dto.AuctionRequest;
import firegruppen.artbid.dto.AuctionResponse;
import firegruppen.artbid.service.AuctionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/auctions")
public class AuctionController {
    AuctionService auctionService;

    public AuctionController(AuctionService auctionService){
        this.auctionService = auctionService;
    }

    //Security USER
    @GetMapping
    List<AuctionResponse> getAuctions(){
        return auctionService.getAuctions();
    }

    //Security USER
    @GetMapping("/{id}")
    public AuctionResponse getAuctionById(@PathVariable int id){
        return auctionService.findById(id);
    }


    //Security ARTIST??
    @PostMapping
    public AuctionResponse addAuction(@RequestBody AuctionRequest body){
        return auctionService.addAuction(body);
    }

    //Security ADMIN
    @PutMapping("/{id}")
    public AuctionResponse editAuction(@RequestBody AuctionRequest body, @PathVariable int id){
        auctionService.editAuction(body, id);
        return auctionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable int id){
        auctionService.deleteAuction(id);
    }


}
