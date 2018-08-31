package us.hyalen.hcode.client.core.auth.v1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResource {
    public static final String MEDIA_TYPE = "application/vnd.hcode.jwt.v1+json";

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResource(String accessToken) {
        this.accessToken = accessToken;
    }
}
