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
public class MemberRequest {
    
    protected String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public MemberRequest(Member m){
        this.username = m.getUsername();
        this.password = m.getPassword();
        this.email = m.getEmail();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
    }

}