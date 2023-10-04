package firegruppen.artbid.service;

import firegruppen.artbid.dto.AuctionRequest;
import firegruppen.artbid.dto.AuctionResponse;
import firegruppen.artbid.entity.Auction;
import firegruppen.artbid.repository.AuctionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuctionServiceTest {

    @Autowired
    AuctionRepository auctionRepository;
    AuctionService auctionService;

    Auction a1, a2, a3;

    @BeforeEach
    void setUp() {
        a1 = auctionRepository.save(new Auction(1, LocalDate.parse("2023-12-03"), LocalDate.parse("2024-01-23"), 5000, 6000,  500));
        a2 = auctionRepository.save(new Auction(2, LocalDate.parse("2023-08-03"), LocalDate.parse("2023-09-23"), 1, 2,  1));
        a3 = auctionRepository.save(new Auction(2, LocalDate.parse("2000-01-01"), LocalDate.parse("2023-01-01"), 2, 4,  2));
        auctionService = new AuctionService(auctionRepository);
    }

    @Test
    void testGetAuctions(){
        List<AuctionResponse> responses = auctionService.getAuctions();
        assertEquals(3, responses.size(), "Expects 3 auctions in the database");
    }

    @Test
    void testFindByIdFound(){
        AuctionResponse response = auctionService.findById(1);
        assertEquals(1, response.getAuctionId(), "Expects a response with auctionId of 1");
    }

    @Test
    void testFindByIdNotFound(){
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> auctionService.findById(-1));
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode(), "Expects NOT_FOUND status code");
    }

    @Test
    void testAddAuction(){
        AuctionRequest request = AuctionRequest.builder()
                .artworkId(5)
                .startDate(LocalDate.parse("2000-01-01"))
                .endDate(LocalDate.parse("2000-02-02"))
                .startBid(1)
                .minimumIncrement(1)
                .build();

        AuctionResponse response = auctionService.addAuction(request);
        assertEquals(4, response.getAuctionId(), "Expects an autoincremented id of 4");
        assertTrue(auctionRepository.existsById(4));
    }

    @Test
    void testAddAuctionAlreadyExistingThrows(){
        AuctionRequest request = new AuctionRequest();
        request.setAuctionId(1);
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> auctionService.addAuction(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    void testEditAuction(){

    }
}