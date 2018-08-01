package us.hyalen.hcode.core.user.v1;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserResource {
    public static final String MEDIA_TYPE = "app/us.hyalen.user.v1+json";

    @NotNull
    public Long userId;
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
    @Size(max = 15)
    public String login;
    @NotNull
    @Size(max = 15)
    public String password;
}
