package firegruppen.artbid.service;

import firegruppen.artbid.dto.ArtworkRequest;
import firegruppen.artbid.dto.ArtworkResponse;
import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.ArtworkRepository;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.security.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArtworkServiceTest {

    @Autowired
    ArtworkRepository artworkRepository;
    ArtworkService artworkService;

    @Autowired
    MemberRepository memberRepository;
    Member m1;

    Artwork art1, art2, art3;
    String defaultImage = "base64_encoded_image_string_here";
    boolean isInitialized;
    @BeforeEach
    void setUp() {
        if(!isInitialized)
            artworkRepository.deleteAll();
            m1 = memberRepository.save(new Member("username1", "Ole", "Olsen", "Olsensvej", "Olsenstad", "0001", "test@m.com", "test123", LocalDateTime.now(), LocalDateTime.now(), true, true, true, new ArrayList<>(Arrays.asList(Role.USER, Role.ADMIN))));
            art1 = artworkRepository.save(new Artwork("Title1", "Category1", "Description1", 10, true, defaultImage, m1));
            art2 = artworkRepository.save(new Artwork("Title2", "Category2", "Description2", 20, false, defaultImage, m1));
            artworkService = new ArtworkService(artworkRepository, memberRepository);
            isInitialized = true;
    }

    @Test
    void getArtwork() {
        List<ArtworkResponse> artworkResponses = artworkService.getArtwork();
        assertEquals(2, artworkResponses.size());
    }

    @Test
    void addArtwork() {
        art3 = artworkRepository.save(new Artwork("Title3", "Category3", "Description3", 30, true, defaultImage));
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