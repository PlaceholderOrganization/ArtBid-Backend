package firegruppen.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import firegruppen.security.entity.UserWithRoles;

@Repository
public interface UserWithRolesRepository extends JpaRepository<UserWithRoles,String> {
    Boolean existsByEmail(String email);

    Optional<UserWithRoles> findById(String username);
}
