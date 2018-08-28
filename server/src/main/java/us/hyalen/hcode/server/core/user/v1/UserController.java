package us.hyalen.hcode.server.core.user.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import us.hyalen.hcode.client.core.user.v1.UserResource;
import us.hyalen.hcode.server.core.ApiResponse;
import us.hyalen.hcode.server.core.NotFoundException;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController("userController_v1")
@RequestMapping(value = "/api/users", produces = UserResource.MEDIA_TYPE)
public class UserController {
    UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserResource> getUserProfile(@PathVariable(value = "username") String username) {
        User user = User.findByUsername((username)).orElseThrow(NotFoundException::new);
        return ok(userMapper.mapDomainToResource(user));
    }

    @PostMapping(consumes = UserResource.MEDIA_TYPE)
    // public void create(@Valid @RequestBody UserResource resource, HttpServletResponse httpServletResponse) {
    public ResponseEntity<?> create(@Valid @RequestBody UserResource resource) {
        User user = new User.Builder().withUserResource(resource).build();
        user = user.create();

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/api/users/{username}")
            .buildAndExpand(user.getUsername()).toUri();

        return created(location).body(new ApiResponse(true, ApiResponse.CREATED));

        // httpServletResponse.setHeader("Location", fromMethodCall(on(getClass()).getUserById(user.getId())).build().toString());
    }

    @PutMapping(value = "/{username}", consumes = UserResource.MEDIA_TYPE)
    public void update(@Valid @RequestBody UserResource resource, @PathVariable(value = "username") String username) {
        User.findByUsername(username).orElseThrow(NotFoundException::new);
        User user = new User.Builder().withUserResource(resource).build();
        user.update();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<?> delete(@PathVariable(value = "username") String username) {
        User user = User.findByUsername(username).orElseThrow(NotFoundException::new);
        user.delete();

        return ok(new ApiResponse(true, ApiResponse.DELETED));
    }
}