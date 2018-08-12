package us.hyalen.hcode.core.user.v1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import us.hyalen.hcode.core.role.v1.RoleResource;
import us.hyalen.hcode.model.RoleModel;
import us.hyalen.hcode.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // ====================== Model to Domain
    @Mapping(target = "userDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    void mapModelToDomain(UserModel model, @MappingTarget User user);

    RoleResource toRole(RoleModel model);

    default User.Builder toDomainBuilder(UserModel model) {
        if (model == null)
            return null;

        return new User.Builder()
                .withId(model.getId())
                .withFirstName(model.getFirstName())
                .withLastName(model.getLastName())
                .withEmail(model.getEmail())
                .withPassword(model.getPassword())
                .withRoles(model.getRoles());
    }

    // ====================== Resource to Domain
    @Mapping(target = "userDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    void mapResourceToDomain(UserResource resource, @MappingTarget User user);

    // ====================== Domain to Model
    @Mapping(target = "UPDATED_AT", ignore = true)
    @Mapping(target = "CREATED_AT", ignore = true)
    UserModel mapDomainToModel(User user);

    // ====================== Domain to Resource
    UserResource mapDomainToResource(User user);
}
