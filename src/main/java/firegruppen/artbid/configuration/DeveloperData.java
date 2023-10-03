package firegruppen.artbid.configuration;

import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.repository.ArtworkRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {

    ArtworkRepository artworkRepository;

    public DeveloperData(ArtworkRepository artworkRepository){
        this.artworkRepository = artworkRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{

        List<Artwork> artworks = new ArrayList<>();

        String defaultImage = "base64_encoded_image_string_here";

        artworks.add(new Artwork("Title", "Category", "Description", 50, true, Collections.singletonList(defaultImage)));
        artworks.add(new Artwork("Title1", "Category1", "Description1", 50, true, Collections.singletonList(defaultImage)));
        artworks.add(new Artwork("Title2", "Category2", "Description2", 50, false, Collections.singletonList(defaultImage)));
        artworkRepository.saveAll(artworks);
    }
}
