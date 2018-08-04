package us.hyalen.hcode.core.user.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.hyalen.hcode.core.NotFoundException;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController("userController_v1")
@RequestMapping(value = "/users", produces = UserResource.MEDIA_TYPE)
public class UserController {
    UserMapper userMapper = UserMapper.INSTANCE;

    @GetMapping(value = "/{id:\\d+}")
    public ResponseEntity<UserResource> getUserById(@PathVariable(value = "id") Long userId) {
        User user = User.findById(userId).orElseThrow(NotFoundException::new);

        return ok(userMapper.mapDomainToResource(user));
    }

    @PostMapping(consumes = UserResource.MEDIA_TYPE)
    public void create(@Valid @RequestBody UserResource resource) {
        // User user = userMapper.mapResourceToDomain(resource).build();
        User user = new User.Builder().withUserResource(resource).build();
        user.save();
    }

    @PutMapping(value = "/{id:\\d+}", consumes = UserResource.MEDIA_TYPE)
    public void update(@Valid @RequestBody UserResource resource, @PathVariable(value = "id") Long userId) {
        User.findById(userId).orElseThrow(NotFoundException::new);
        // User user = userMapper.mapResourceToDomain(resource).build();
        User user = new User.Builder().withUserResource(resource).build();
        user.update();
    }

    @DeleteMapping(value = "/{id:\\d+}")
    public void delete(@PathVariable(value = "id") Long userId) {
        User user = User.findById(userId).orElseThrow(NotFoundException::new);
        user.delete(userId);
    }
}
