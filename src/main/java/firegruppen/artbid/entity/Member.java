package firegruppen.artbid.entity;

import firegruppen.artbid.dto.MemberRequest;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    

    @NonNull
    @Column(length = 25, name = "first_name")
    private String firstName;
    @NonNull
    @Column(length = 25, name = "last_name")
    private String lastName;
    @Column(length = 45)
    private String street;
    @Column(length = 45)
    private String city;
    @Column(length = 10)
    private String zipCode;

    @OneToMany(mappedBy = "member")
    List<Review> reviews;

//    @OneToMany(mappedBy = "member")
//    List<Bid> bids;

    public void addReview(Review review) {
        if(reviews==null){
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

//    public void addBid(Bid bid) {
//        if(bids==null){
//            bids = new ArrayList<>();
//        }
//        bids.add(bid);
//    }

    public Member(String username, String firstName, String lastName, String street, String city, String zipCode, String email, String password, LocalDateTime created, LocalDateTime updated, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, List<Role> roles) {
        super(username, email, password, created, updated, enabled, accountNonExpired, accountNonLocked, roles);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }  

    // Inside Member class
    public static Member getMemberEntity(MemberRequest request) {
    // Convert MemberRequest to Member and return
    Member member = new Member();
    member.setUsername(request.getUsername());
    member.setFirstName(request.getFirstName());
    member.setLastName(request.getLastName());
    member.setStreet(request.getStreet());
    member.setCity(request.getCity());
    member.setZipCode(request.getZipCode());
    member.setEmail(request.getEmail());
    member.setPassword(request.getPassword());
    return member;
    }

    public Member(String string, String string2, String string3, String string4, String string5, String string6,
            String string7, String string8, String string9, String string10, boolean b, boolean c, boolean d, boolean e,
            String string11) {
    }

    public Member(String username, String password, String email, String firstName, String lastName, String street, String city, String zip) {
    }
}
