package us.hyalen.hcode.server.core.auth.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import us.hyalen.hcode.client.core.auth.v1.AuthResource;
import us.hyalen.hcode.client.core.auth.v1.JwtAuthenticationResource;
import us.hyalen.hcode.client.core.user.v1.UserResource;
import us.hyalen.hcode.server.core.ApiResponse;
import us.hyalen.hcode.server.core.NotFoundException;
import us.hyalen.hcode.server.core.role.v1.Role;
import us.hyalen.hcode.server.core.role.v1.RoleMapper;
import us.hyalen.hcode.server.core.user.v1.User;
import us.hyalen.hcode.server.model.RoleName;
import us.hyalen.hcode.server.security.JwtTokenProvider;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

import static org.springframework.http.ResponseEntity.created;

@RestController("authController_v1")
@RequestMapping(value = "/api/auth", produces = AuthResource.MEDIA_TYPE)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthResource authResource) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authResource.getUsernameOrEmail(),
                        authResource.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResource(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserResource userResource) {
        if (User.existsByUsername(userResource.username))
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);

        if (User.existsByEmail(userResource.email))
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);

        // Creating user's account
        Role role = Role.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new NotFoundException("User Role not set."));

        userResource.password = passwordEncoder.encode(userResource.password);
        userResource.roles = Collections.singleton(RoleMapper.INSTANCE.mapDomainToResource(role));

        User user = new User.Builder().withUserResource(userResource).build();
        user = user.create();

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return created(location).body(new ApiResponse(true, ApiResponse.CREATED));
    }
}
