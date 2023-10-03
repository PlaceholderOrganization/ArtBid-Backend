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
        List<Review> reviews = reviewRepository.findAllByArtwork_ArtworkId(artworkId);
        List<ReviewResponse> response = reviews.stream().map(ReviewResponse::new).toList();
        return response;
    }
    public void addReview(ReviewRequest reviewRequest) {
        Artwork artwork = artworkRepository.findById(reviewRequest.getArtworkId())
                .orElseThrow(()-> new EntityNotFoundException("Artwork not found"));
        Member member = memberRepository.findById(reviewRequest.getUsername())
                .orElseThrow(()-> new EntityNotFoundException("Member not found"));
        Review review = new Review();
        review.setDescription(reviewRequest.getDescription());
        review.setRating(reviewRequest.getRating());
        review.setDate(reviewRequest.getDate());
        review.setArtwork(artwork);
        review.setMember(member);

        reviewRepository.save(review);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews= reviewRepository.findAll();
        List<ReviewResponse> response = reviews.stream().map(ReviewResponse::new).toList();
        return response;
    }
}