package firegruppen.artbid.service;

import firegruppen.artbid.dto.ArtworkRequest;
import firegruppen.artbid.dto.ArtworkResponse;
import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.repository.ArtworkRepository;
import firegruppen.artbid.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArtworkService {

    ArtworkRepository artworkRepository;
    MemberRepository memberRepository;

    public ArtworkService(ArtworkRepository artworkRepository, MemberRepository memberRepository) {
        this.artworkRepository = artworkRepository;
        this.memberRepository = memberRepository;
    }


    //Get Artwork
    public List<ArtworkResponse> getArtwork(){
        List<Artwork> artworks = artworkRepository.findAll();
        List<ArtworkResponse> responses = new ArrayList<>();
        for(Artwork artwork : artworks){
            ArtworkResponse artworkResponse = new ArtworkResponse(artwork);
            responses.add(artworkResponse);
        }
        return responses;
    }
    //Add Artwork
    public ArtworkResponse addArtwork(ArtworkRequest bodyArt){
        if(artworkRepository.existsById(bodyArt.getArtworkId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Artwork already exist");
        }
        Member member = memberRepository.findById(bodyArt.getUsername()).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member not found"));
        Artwork newArtwork = ArtworkRequest.getArtworkEntity(bodyArt);
        newArtwork.setMember(member);
        newArtwork = artworkRepository.save(newArtwork);

        return new ArtworkResponse(newArtwork);
    }

    //Find Artwork
    public ArtworkResponse findArtById(int artworkId){
        Artwork artwork = artworkRepository.findById(artworkId).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artwork with this id does not exist"));
        return  new ArtworkResponse(artwork);
    }
    //Edit Artwork
    public ResponseEntity<Boolean> editArtwork(ArtworkRequest bodyArt, int id){
        System.out.println(id);
        Artwork artwork = artworkRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artwork with this id does not exist"));
        if(bodyArt.getArtworkId()!=id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not allowed to change id");
        }
        artwork.setTitle(bodyArt.getTitle());
        artwork.setCategory(bodyArt.getCategory());
        artwork.setDescription(bodyArt.getDescription());
        artwork.setPrice(bodyArt.getPrice());
        artwork.setForSale(bodyArt.isForSale());
        artwork.setImage(bodyArt.getImage());
        artworkRepository.save(artwork);
        return ResponseEntity.ok(true);
    }

    //Delete Artwork
    public ResponseEntity<Boolean> deleteArtwork(int artworkId){
        if(!artworkRepository.existsById(artworkId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Artwork with that id does not exist");
        }
        artworkRepository.deleteById(artworkId);
        return ResponseEntity.ok(true);
    }

    //Get Artwork by Category

    //Get Artwork by Artist Name
}
