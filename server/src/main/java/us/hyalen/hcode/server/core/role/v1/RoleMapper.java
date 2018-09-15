package us.hyalen.hcode.server.core.role.v1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import us.hyalen.hcode.client.core.role.v1.RoleResource;
import us.hyalen.hcode.server.model.RoleModel;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    // ====================== Model to Domain
    default Set<Role> toRolesFromModels(Set<RoleModel> models) {
        Set<Role> roles = new HashSet<>();

        if (models != null && models.size() > 0)
            models.forEach (roleModel -> roles.add(new Role.Builder().withRoleModel(roleModel).build()));
        // models.forEach (roleModel -> roles.add(toRole(roleModel)));

        return roles;
    }

    @Mapping(target = "roleDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    void mapModelToDomain(RoleModel model, @MappingTarget Role role);

    default Role.Builder toDomainBuilder(RoleModel model) {
        if (model == null)
            return null;

        return new Role.Builder()
                .withId(model.getId())
                .withName(model.getName());
    }

    // ====================== Resource to Domain
    default Set<Role> toRolesFromResources(Set<RoleResource> resources) {
        Set<Role> roles = new HashSet<>();

        if (resources != null && resources.size() > 0)
            resources.forEach (roleResource -> roles.add(new Role.Builder().withRoleResource(roleResource).build()));
        // models.forEach (roleModel -> roles.add(toRole(roleModel)));

        return roles;
    }

    @Mapping(target = "roleDao", ignore = true)
    @Mapping(target = "validator", ignore = true)
    @Mapping(target = "validationErrors", ignore = true)
    default Role.Builder mapResourceToDomain(RoleResource resource) {
        return new Role.Builder()
                .withId(resource.id)
                .withName(resource.name);
    }

    // ====================== Domain to Model
    RoleModel mapDomainToModel(Role role);

    // ====================== Domain to Resource
    RoleResource mapDomainToResource(Role role);
}