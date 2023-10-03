package firegruppen.artbid.repository;

import firegruppen.artbid.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByArtwork_ArtworkId(Integer artworkId);
}
