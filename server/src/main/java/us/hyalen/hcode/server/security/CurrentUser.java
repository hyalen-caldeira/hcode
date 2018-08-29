package us.hyalen.hcode.server.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
/**
 * Spring security provides an annotation called @AuthenticationPrincipal to access the currently authenticated
 * user in the controllers. The following CurrentUser annotation is a wrapper around @AuthenticationPrincipal annotation.
 */
public @interface CurrentUser {}