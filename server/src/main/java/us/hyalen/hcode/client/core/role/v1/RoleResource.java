package us.hyalen.hcode.client.core.role.v1;

import lombok.EqualsAndHashCode;
import us.hyalen.hcode.server.model.RoleName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode
public class RoleResource {
    public static final String MEDIA_TYPE = "app/us.hyalen.role.v1+json";

    @NotNull
    public Long id;
    @NotNull
    @Size(max = 40)
    public RoleName name;
}