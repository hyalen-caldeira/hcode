package us.hyalen.hcode.server.core.user.v1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import us.hyalen.hcode.client.core.user.v1.UserResource;
import us.hyalen.hcode.server.core.role.v1.RoleMapper;
import us.hyalen.hcode.server.model.UserModel;
import us.hyalen.hcode.server.security.UserPrincipal;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // ====================== Model to Domain
//    @Mapping(target = "userDao", ignore = true)
//    @Mapping(target = "validator", ignore = true)
//    @Mapping(target = "validationErrors", ignore = true)
//    void mapModelToDomain(UserModel model, @MappingTarget User user);

    @Mapping(target = "roleDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    default User.Builder toDomainBuilder(UserModel model) {
        if (model == null)
            return null;

        return new User.Builder()
                .withId(model.getId())
                .withFirstName(model.getFirstName())
                .withLastName(model.getLastName())
                .withEmail(model.getEmail())
                .withUsername(model.getUsername())
                .withPassword(model.getPassword())
                .withRoles(RoleMapper.INSTANCE.toRolesFromModels(model.getRoles()));
    }

    // ====================== Resource to Domain
//    @Mapping(target = "userDao", ignore = true)
//    @Mapping(target = "validator", ignore = true)
//    @Mapping(target = "validationErrors", ignore = true)
//    void mapResourceToDomain(UserResource resource, @MappingTarget User user);

    // TODO, fix the withRoles method
    @Mapping(target = "userDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    default User.Builder mapResourceToDomain(UserResource resource) {
        return new User.Builder()
                .withId(resource.id)
                .withFirstName(resource.firstName)
                .withLastName(resource.lastName)
                .withEmail(resource.email)
                .withUsername(resource.username)
                .withPassword(resource.password)
                .withRoles(RoleMapper.INSTANCE.toRolesFromResources(resource.roles));
    }

    // ====================== Domain to Model
    @Mapping(target = "UPDATED_AT", ignore = true)
    @Mapping(target = "CREATED_AT", ignore = true)
    // @Mapping(target = "roles", ignore = true)
    UserModel mapDomainToModel(User user);

    // ====================== Domain to Resource
    UserResource mapDomainToResource(User user);

    // ====================== Domain to UserPrincipal
    @Mapping(target = "authorities", ignore = true)
    void mapDomainToUserPrincipal(User user, @MappingTarget UserPrincipal userPrincipal);
}
