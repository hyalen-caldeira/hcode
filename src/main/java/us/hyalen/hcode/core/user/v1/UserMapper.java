package us.hyalen.hcode.core.user.v1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import us.hyalen.hcode.model.UserModel;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // ====================== Model to DomainBuilder
    default User.Builder mapModelToDomainBuilder(UserModel model) {
        if (model == null)
            return null;

        return new User.Builder()
                .withUserId(model.getUserId())
                .withFirstName(model.getFirstName())
                .withLastName(model.getLastName())
                .withEmail(model.getEmail())
                .withLogin(model.getLogin())
                .withPassword(model.getPassword());
    }

    // ====================== Resource to DomainBuilder
    default User.Builder mapResourceToDomainBuilder(UserResource resource) {
        return new User.Builder()
                .withUserId(resource.getUserId())
                .withFirstName(resource.getFirstName())
                .withLastName(resource.getLastName())
                .withEmail(resource.getEmail())
                .withLogin(resource.getLogin())
                .withPassword(resource.getPassword());
    }

    // ====================== Domain to Model
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    UserModel mapDomainToModel(User user);


    // ====================== Domain to Resource
    UserResource mapDomainToResource(User user);
}
