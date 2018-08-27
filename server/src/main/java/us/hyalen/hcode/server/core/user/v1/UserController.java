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

    @GetMapping(value = "/{id:\\d+}")
    public ResponseEntity<UserResource> getUserById(@PathVariable(value = "id") Long userId) {
        User user = User.findById(userId).orElseThrow(NotFoundException::new);
        return ok(userMapper.mapDomainToResource(user));
    }

    @GetMapping(value = "/{login:^[0-9a-fA-F]{24}$}")
    public ResponseEntity<UserResource> getUserProfile(@PathVariable(value = "login") String login) {
        User user = User.findByLogin((login)).orElseThrow(NotFoundException::new);
        return ok(userMapper.mapDomainToResource(user));
    }

    @PostMapping(consumes = UserResource.MEDIA_TYPE)
    // public void create(@Valid @RequestBody UserResource resource, HttpServletResponse httpServletResponse) {
    public ResponseEntity<?> create(@Valid @RequestBody UserResource resource) {
        User user = new User.Builder().withUserResource(resource).build();
        user = user.create();

        URI location = ServletUriComponentsBuilder
            .fromCurrentContextPath().path("/api/users/{login}")
            .buildAndExpand(user.getLogin()).toUri();

        return created(location).body(new ApiResponse(true, ApiResponse.CREATED));

        // httpServletResponse.setHeader("Location", fromMethodCall(on(getClass()).getUserById(user.getId())).build().toString());
    }

    @PutMapping(value = "/{id:\\d+}", consumes = UserResource.MEDIA_TYPE)
    public void update(@Valid @RequestBody UserResource resource, @PathVariable(value = "id") Long userId) {
        User.findById(userId).orElseThrow(NotFoundException::new);
        User user = new User.Builder().withUserResource(resource).build();
        user.update();
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {
        User user = User.findById(userId).orElseThrow(NotFoundException::new);
        user.delete();

        return ok(new ApiResponse(true, ApiResponse.DELETED));
    }
}