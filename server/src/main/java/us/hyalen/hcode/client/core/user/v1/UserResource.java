package us.hyalen.hcode.client.core.user.v1;

import lombok.EqualsAndHashCode;
import us.hyalen.hcode.client.core.role.v1.RoleResource;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@EqualsAndHashCode
public class UserResource {
    public static final String MEDIA_TYPE = "application/vnd.hcode.users.v1+json";

    public Long id;
    @NotBlank
    @Size(max = 20)
    public String firstName;
    @NotBlank
    @Size(max = 20)
    public String lastName;
    @NotBlank
    @Size(max = 40)
    @Email
    public String email;
    @NotBlank
    @Size(max = 40)
    public String username;
    @NotBlank
    @Size(max = 100)
    public String password;
    public Set<RoleResource> roles;
}
