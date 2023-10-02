package firegruppen.artbid.service;

import firegruppen.artbid.dto.ArtworkResponse;
import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.repository.ArtworkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArtworkServiceTest {

    @Autowired
    ArtworkRepository artworkRepository;
    ArtworkService artworkService;

    Artwork art1, art2, art3;

    boolean isInitialized;
    @BeforeEach
    void setUp() {
        if(!isInitialized)
            artworkRepository.deleteAll();
            art1 = artworkRepository.save(new Artwork("Title1", "Category1", "Description1", 10, true));
            art2 = artworkRepository.save(new Artwork("Title2", "Category2", "Description2", 20, false));
            artworkService = new ArtworkService(artworkRepository);
            isInitialized = true;
    }

    @Test
    void getArtwork() {
        List<ArtworkResponse> artworkResponses = artworkService.getArtwork();
        assertEquals(2, artworkResponses.size());
    }

    @Test
    void addArtwork() {
        art3 = artworkRepository.save(new Artwork("Title3", "Category3", "Description3", 30, true));
        artworkService = new ArtworkService(artworkRepository);
        assertEquals("Title3", art3.getTitle());
        assertEquals("Description3", art3.getDescription());
    }

}