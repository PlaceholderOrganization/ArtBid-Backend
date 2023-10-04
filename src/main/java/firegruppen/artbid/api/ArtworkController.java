package firegruppen.artbid.api;

import firegruppen.artbid.dto.ArtworkRequest;
import firegruppen.artbid.dto.ArtworkResponse;
import firegruppen.artbid.service.ArtworkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/artwork")
public class ArtworkController {

    ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @GetMapping
    List<ArtworkResponse> getArtwork(){
        return artworkService.getArtwork();
    }

    @PostMapping()
    ArtworkResponse addArtwork(@RequestBody ArtworkRequest bodyArt){
        return artworkService.addArtwork(bodyArt);
    }

    @GetMapping("/{id}")
    ArtworkResponse getArtById(@PathVariable int id)throws Exception{
        return artworkService.findArtById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> editArtwork(@RequestBody ArtworkRequest bodyArt, @PathVariable int id){
        return artworkService.editArtwork(bodyArt, id);
    }

    @DeleteMapping("/{artworkId}")
    ResponseEntity<Boolean> deleteArtwork(@PathVariable int artworkId){
        return artworkService.deleteArtwork(artworkId);
    }
}
