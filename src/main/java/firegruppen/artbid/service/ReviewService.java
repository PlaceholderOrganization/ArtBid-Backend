package firegruppen.artbid.service;

import firegruppen.artbid.dto.ReviewRequest;
import firegruppen.artbid.dto.ReviewResponse;
import firegruppen.artbid.entity.Artwork;
import firegruppen.artbid.entity.Member;
import firegruppen.artbid.entity.Review;
import firegruppen.artbid.repository.ArtworkRepository;
import firegruppen.artbid.repository.MemberRepository;
import firegruppen.artbid.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        List<Review> reviews = reviewRepository.findAllByArtwork_ArtworkId(artworkId);
        List<ReviewResponse> response = reviews.stream().map(ReviewResponse::new).toList();
        return response;
    }
    public void addReview(ReviewRequest reviewRequest) {
        Artwork artwork = artworkRepository.findById(reviewRequest.getArtworkId())
                .orElseThrow(()-> new EntityNotFoundException("Artwork not found"));
        Member member = memberRepository.findById(reviewRequest.getUsername())
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        Review review = ReviewRequest.getReviewEntity(reviewRequest);
        review.setArtwork(artwork);
        review.setMember(member);
        reviewRepository.save(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews= reviewRepository.findAll();
        List<ReviewResponse> response = reviews.stream().map(ReviewResponse::new).toList();
        return response;
    }

    public void addReviewToArtwork(ReviewRequest reviewRequest, int artworkId) {
        Artwork artwork = artworkRepository.findById(artworkId)
                .orElseThrow(()-> new EntityNotFoundException("Artwork not found"));
        Member member = memberRepository.findById(reviewRequest.getUsername())
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        if(reviewRepository.existsReviewByArtwork_ArtworkIdAndMember_Username(artworkId,reviewRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't add multiple reviews");
        }
        Review review = ReviewRequest.getReviewEntity(reviewRequest);
        review.setArtwork(artwork);
        review.setMember(member);
        reviewRepository.save(review);
    }

    public ResponseEntity<Boolean> editReview(ReviewRequest body, int reviewId) {
        Review review =reviewRepository.findById(reviewId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Review not found"));
        System.out.println(reviewId + " test1");
            if(body.getId()!=reviewId) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Can't change id");
            }
            review.setRating(body.getRating());
            review.setDate(body.getDate());
            review.setDescription(body.getDescription());
        System.out.println(reviewId + " test2 after setting");
            reviewRepository.save(review);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<Boolean> deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
        return ResponseEntity.ok(true);
    }
}
