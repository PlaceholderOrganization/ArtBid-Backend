package firegruppen.artbid.service;

import firegruppen.artbid.dto.ArtworkRequest;
import firegruppen.artbid.dto.ArtworkResponse;
import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.repository.ArtworkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArtworkServiceTest {

    @Autowired
    ArtworkRepository artworkRepository;
    ArtworkService artworkService;

    Artwork art1, art2, art3;
    String defaultImage = "base64_encoded_image_string_here";
    boolean isInitialized;
    @BeforeEach
    void setUp() {
        if(!isInitialized)
            artworkRepository.deleteAll();
            art1 = artworkRepository.save(new Artwork("Title1", "Category1", "Description1", 10, true, Collections.singletonList(defaultImage)));
            art2 = artworkRepository.save(new Artwork("Title2", "Category2", "Description2", 20, false, Collections.singletonList(defaultImage)));
            artworkService = new ArtworkService(artworkRepository, artworkService.memberRepository);
            isInitialized = true;
    }

    @Test
    void getArtwork() {
        List<ArtworkResponse> artworkResponses = artworkService.getArtwork();
        assertEquals(2, artworkResponses.size());
    }

    @Test
    void addArtwork() {
        art3 = artworkRepository.save(new Artwork("Title3", "Category3", "Description3", 30, true, Collections.singletonList(defaultImage)));
        artworkService = new ArtworkService(artworkRepository, artworkService.memberRepository);
        assertEquals("Title3", art3.getTitle());
        assertEquals("Description3", art3.getDescription());
    }

    //Add with existing id
//    @Test
//    void addArtworkWithExistingId(){
//        ArtworkRequest request = new ArtworkRequest(art1.getArtworkId(), "Title1", "Category1", "Description1", 10, true, Collections.singletonList(defaultImage));
//    }
    @Test
    void findByIdFound(){
        ArtworkResponse response = artworkService.findArtById(art1.getArtworkId());
        assertEquals("Category1", response.getCategory());
        assertEquals(10, response.getPrice());
    }



}