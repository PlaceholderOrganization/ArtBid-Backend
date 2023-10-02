package firegruppen.security.api;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import firegruppen.security.dto.LoginRequest;
import firegruppen.security.dto.LoginResponse;
import firegruppen.security.entity.Role;
import firegruppen.security.entity.UserWithRoles;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin
public class AuthenticationController {

    @Value("${app.token-issuer}")
    private String tokenIssuer;
    @Value("1800")
    private long tokenExpiration;

    private AuthenticationManager authenticationManager;

    JwtEncoder encoder;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken uat = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            Authentication authentication = authenticationManager.authenticate(uat);

            UserWithRoles user = (UserWithRoles) authentication.getPrincipal();
            Instant now = Instant.now();
            long expiry = tokenExpiration;
            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer(tokenIssuer)  //Only this for simplicity
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(tokenExpiration))
                    .subject(user.getUsername())
                    .claim("roles", scope)
                    .build();

            JwsHeader jwsHeader = JwsHeader.with(() -> "HS256").build();
            String token = encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();

            //LoginResponse response = new LoginResponse(jwt.getTokenValue(), jwt.getExpiresAt().toEpochMilli());
            List<String> roles = user.getRoles().stream().map(Role::toString).collect(Collectors.toList());
            return ResponseEntity.ok().body(new LoginResponse(user.getUsername(), token, roles));

        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username/password supplied");
        }
    }
}