package us.hyalen.hcode.server.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import us.hyalen.hcode.server.core.user.v1.User;
import us.hyalen.hcode.server.core.user.v1.UserMapper;

import java.util.Collection;

@Getter
@EqualsAndHashCode
public class UserPrincipal implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class Builder {
        private UserPrincipal userPrincipal;

        public Builder() {
            this.userPrincipal = new UserPrincipal();
        }

        public Builder(UserPrincipal userPrincipal) {
            this.userPrincipal = userPrincipal;
        }

        public Builder withUser(User user) {
            UserMapper.INSTANCE.mapDomainToUserPrincipal(user, userPrincipal);
            return this;
        }

        public Builder withId(Long id) {
            userPrincipal.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            userPrincipal.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            userPrincipal.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            userPrincipal.email = email;
            return this;
        }

        public Builder withUsername(String username) {
            userPrincipal.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            userPrincipal.password = password;
            return this;
        }

        public Builder withAuthorities(Collection<? extends GrantedAuthority> authorities) {
            userPrincipal.authorities = authorities;
            return this;
        }

        public UserPrincipal build() {
            return userPrincipal;
        }
    }
}
