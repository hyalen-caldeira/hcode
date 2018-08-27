package us.hyalen.hcode.server.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;

    public static final String CREATED = "Resource registered successfully.";
    public static final String UPDATED = "Resource updated successfully.";
    public static final String DELETED = "User deleted successfully.";

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
