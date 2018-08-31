package us.hyalen.hcode.client.core.auth.v1;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthResource {
    public static final String MEDIA_TYPE = "application/vnd.hcode.login.v1+json";

    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
}
