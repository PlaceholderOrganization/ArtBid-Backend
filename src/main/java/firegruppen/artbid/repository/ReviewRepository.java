package firegruppen.artbid.repository;

import firegruppen.artbid.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByArtwork(int artworkId);
}
