package firegruppen.artbid.api;

import firegruppen.artbid.dto.ReviewRequest;
import firegruppen.artbid.dto.ReviewResponse;
import firegruppen.artbid.service.ReviewService;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("")
    public List<ReviewResponse> getAllReviews() throws Exception {
        return reviewService.getAllReviews();
    }

//    @PostMapping("")
//    public void addReview(@RequestBody ReviewRequest body) {
//        reviewService.addReview(body);
//    }

    @PostMapping("/{artworkId}")
    public void addReviewToArtwork(@RequestBody ReviewRequest body, @PathVariable int artworkId) {
        reviewService.addReviewToArtwork(body,artworkId);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Boolean> editReview(@RequestBody ReviewRequest body, @PathVariable int reviewId) {
        return reviewService.editReview(body,reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable int reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
