package firegruppen.security.configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;

import firegruppen.security.error.CustomOAuth2AccessDeniedHandler;
import firegruppen.security.error.CustomOAuth2AuthenticationEntryPoint;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    // TODO 1: Can add default token value. Remove before deploy
    @Value("${app.secret-key}")
    private String tokenSecret;

    @Autowired
    CorsConfigurationSource corsConfigurationSource;

    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http
                .cors(Customizer.withDefaults()) //Will use the CorsConfigurationSource bean declared in CorsConfig.java
                //.cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                //.httpBasic(httpBasic -> httpBasic.disable())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer
                        .jwt((jwt) -> jwt.decoder(jwtDecoder())
                                .jwtAuthenticationConverter(authenticationConverter())
                        )
                        .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler())
                );
        http.authorizeHttpRequests(authotize -> authotize
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/auth/login")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/user-with-roles")).permitAll() //Clients can create user for themselves

                // TODO 2: This is demo code, remove it before deployment
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/demo/anonymous")).permitAll()

                // Allow index.html and everything else on root level.
                // So make sure to put ALL your endpoints under /api
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/*")).permitAll()

                .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()

                //TODO 3: This is for demo. Remove before deploy
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/test/user-only")).hasAuthority("USER")
                .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/test/admin-only")).hasAuthority("ADMIN")

                // Use this to completely disable security
                //.requestMatchers(mvcMatcherBuilder.pattern("/**")).permitAll()
                .anyRequest().authenticated()


                .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(secretKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
      return new NimbusJwtEncoder(
              new ImmutableSecret<SecurityContext>(secretKey())
      );
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}