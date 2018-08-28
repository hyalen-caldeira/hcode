package us.hyalen.hcode.client.core.role.v1;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RoleResource {
    public static final String MEDIA_TYPE = "app/us.hyalen.role.v1+json";

    @NotNull
    public Long id;
    @NotNull
    @Size(max = 40)
    public String name;
}
