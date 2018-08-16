package us.hyalen.hcode.client.core.user.v1;

import lombok.Data;
import us.hyalen.hcode.client.core.role.v1.RoleResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserResource {
    public static final String MEDIA_TYPE = "application/vnd.hcode.users.v1+json";

    public Long id;
    @NotNull
    @Size(max = 20)
    public String firstName;
    @NotNull
    @Size(max = 20)
    public String lastName;
    @NotNull
    @Size(max = 40)
    public String email;
    @NotNull
    @Size(max = 40)
    public String login;
    @NotNull
    @Size(max = 20)
    public String password;
    public List<RoleResource> roles;
}