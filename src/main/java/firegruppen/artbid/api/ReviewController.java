package firegruppen.artbid.api;

import firegruppen.artbid.dto.ReviewResponse;
import firegruppen.artbid.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/review")
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{artworkId}")
    public List<ReviewResponse> getReviews(@PathVariable int artworkId) throws Exception {
        return reviewService.getReviewsByArtwork(artworkId);
    }
}
