package firegruppen.artbid.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import firegruppen.artbid.entity.Member;
import firegruppen.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zipCode;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private List<Role> roles;

    public MemberResponse(Member m, boolean includeRoles, boolean includeAccountStatus){
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.street = m.getStreet();
        this.city = m.getCity();
        this.zipCode = m.getZipCode();

        if (includeRoles){
            this.roles = m.getRoles();
        }

        if (includeAccountStatus){
            this.enabled = m.isEnabled();
            this.accountNonExpired = m.isAccountNonExpired();
            this.accountNonLocked = m.isAccountNonLocked();
        }

    }
}