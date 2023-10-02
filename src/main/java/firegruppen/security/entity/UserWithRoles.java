package firegruppen.security.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configurable
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR_TYPE")
public class UserWithRoles implements UserDetails {

    /*
    * This is not elegant since the password encoder is hardcoded, and eventually could end as being different from the one used in the project
    * It's done this way, to make it easier to use this semester, since this class hashes and salts passwords automatically
    * Also it's done like this since YOU CANNOT inject beans into entities
    * */
    @Transient
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @Column(nullable = false,length = 50, unique = true, name = "username")
    String username;

    @Column(nullable = false,length = 50, name = "user_email")
    String email;

    //60 = length of a bcrypt encoded password
    @Column(nullable = false, length = 60, name = "user_password")
    String password;

    private boolean enabled= true;

    @CreationTimestamp
    @Column(name = "user_created")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "user_updated")
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN')")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "security_role")
    List<Role> roles = new ArrayList<>();

    public UserWithRoles() {
    }

    public UserWithRoles(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public UserWithRoles(String username, String email, String password, LocalDateTime created, LocalDateTime updated, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, List<Role> roles) {
        this.username = username;
        this.email = email;
        setPassword(password);
        this.created = created;
        this.updated = updated;
        this.enabled = enabled;
        this.roles = new ArrayList<>(roles);
    }
    
    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    public void addRole(Role roleToAdd){
        if (!roles.contains(roleToAdd)){
            roles.add(roleToAdd);
        }
    }

    public void removeRole(Role roleToRemove){
        if (roles.contains(roleToRemove)){
            roles.remove(roleToRemove);
        }
    }

    // You can, but are not expected to use the fields below
    // TODO 5: Implement the methods below

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}