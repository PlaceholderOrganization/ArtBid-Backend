package firegruppen.security.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import firegruppen.security.dto.UserWithRolesRequest;
import firegruppen.security.dto.UserWithRolesResponse;
import firegruppen.security.entity.Role;
import firegruppen.security.service.UserWithRolesService;

@RestController
@RequestMapping("/api/user-with-roles")
public class UserWithRolesController {

    // Take care. If no role is required for new users, add null as the value below
    static Role DEFAULT_ROLE_TO_ASSIGN = Role.USER;

    UserWithRolesService userWithRolesService;

    public UserWithRolesController(UserWithRolesService userWithRolesService) {
        this.userWithRolesService = userWithRolesService;
    }

    // Anonymous users can call this. Set DEFAULT_ROLE_TO_ASSIGN to null if no role should be added
    @PostMapping
    public UserWithRolesResponse addUserWithRoles(@RequestBody UserWithRolesRequest request) {
        return userWithRolesService.addUserWithRoles(request, DEFAULT_ROLE_TO_ASSIGN);
    }

    // Take care. This should NOT be something everyone can call
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/add-role/{username}/{role}")
    public UserWithRolesResponse addRole(@PathVariable String username, @PathVariable String role) {
        return userWithRolesService.addRole(username, Role.fromString(role));
    }

    // Take care. This should NOT be something everyone can call
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/remove-role/{username}/{role}")
    public UserWithRolesResponse removeRole(@PathVariable String username, @PathVariable String role) {
        return userWithRolesService.removeRole(username, Role.fromString(role));
    }
}
