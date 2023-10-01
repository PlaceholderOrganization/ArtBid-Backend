package firegruppen.artbid.repository;

import firegruppen.artbid.entity.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {

}
