package firegruppen.artbid.entity;

import java.time.LocalDateTime;
import java.util.List;

import firegruppen.artbid.dto.MemberRequest;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {
    

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Column(length = 45)
    private String street;
    @Column(length = 45)
    private String city;
    @Column(length = 10)
    private String zipCode;



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

}
