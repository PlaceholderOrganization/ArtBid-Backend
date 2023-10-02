package firegruppen.security.dto;

import java.util.List;
import java.util.stream.Collectors;

import firegruppen.security.entity.UserWithRoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithRolesResponse {

    String userName;
    List<String> roleNames;
    String email;

    public UserWithRolesResponse(UserWithRoles userWithRoles){
        this.userName = userWithRoles.getUsername();
        this.roleNames = userWithRoles.getRoles().stream().map(role -> role.toString()).collect(Collectors.toList());
        this.email = userWithRoles.getEmail();
    }
}