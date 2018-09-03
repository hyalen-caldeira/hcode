package us.hyalen.hcode.server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.hyalen.hcode.server.core.user.v1.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = User
            .findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)
            );

        return new UserPrincipal.Builder().withAuthorities(getAuthorities(user)).withUser(user).build();
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = User.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return new UserPrincipal.Builder().withAuthorities(getAuthorities(user)).withUser(user).build();
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        return user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}
