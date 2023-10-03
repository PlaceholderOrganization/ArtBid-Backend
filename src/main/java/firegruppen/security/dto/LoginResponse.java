package firegruppen.security.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    private String username;
    private String token;
    private List<String> roles;

    public LoginResponse(String userName, String token, List<String> roles) {
        this.username = userName;
        this.token = token;
        this.roles = roles;
    }

    public LoginResponse(String tokenValue, long epochMilli) {
    }
}