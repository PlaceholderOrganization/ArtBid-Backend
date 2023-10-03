package firegruppen.artbid.service;

import firegruppen.artbid.dto.ReviewRequest;
import firegruppen.artbid.dto.ReviewResponse;
import firegruppen.artbid.entity.Review;
import firegruppen.artbid.repository.ArtworkRepository;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.artbid.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    MemberRepository memberRepository;
    ArtworkRepository artworkRepository;

    ReviewRepository reviewRepository;

    public ReviewService(MemberRepository memberRepository, ArtworkRepository artworkRepository, ReviewRepository reviewRepository) {
        this.memberRepository = memberRepository;
        this.artworkRepository = artworkRepository;
        this.reviewRepository = reviewRepository;
    }


    public List<ReviewResponse> getReviewsByArtwork(int artworkId) {
        List<Review> reviews = reviewRepository.findAllByArtwork(artworkId);
        List<ReviewResponse> response = reviews.stream().map(ReviewResponse::new).toList();
        return response;
    }
    public void addReview(ReviewRequest reviewRequest) {
        Review newReview = ReviewRequest.getReviewEntity(reviewRequest);
        newReview = reviewRepository.save(newReview);
    }
}