package firegruppen.artbid.dto;

import firegruppen.artbid.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public MemberResponse(Member m){
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
    }
}