package us.hyalen.hcode.server.core.user.v1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import us.hyalen.hcode.server.core.role.v1.RoleResource;
import us.hyalen.hcode.server.model.RoleModel;
import us.hyalen.hcode.server.model.RoleName;
import us.hyalen.hcode.server.model.UserModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-13T23:31:04-0700",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void mapModelToDomain(UserModel model, User user) {
        if ( model == null ) {
            return;
        }

        user.setId( model.getId() );
        user.setFirstName( model.getFirstName() );
        user.setLastName( model.getLastName() );
        user.setEmail( model.getEmail() );
        user.setLogin( model.getLogin() );
        user.setPassword( model.getPassword() );
        if ( user.getRoles() != null ) {
            Set<RoleResource> set = roleModelSetToRoleResourceSet( model.getRoles() );
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
            else {
                user.setRoles( null );
            }
        }
        else {
            Set<RoleResource> set = roleModelSetToRoleResourceSet( model.getRoles() );
            if ( set != null ) {
                user.setRoles( set );
            }
        }
    }

    @Override
    public RoleResource toRole(RoleModel model) {
        if ( model == null ) {
            return null;
        }

        RoleResource roleResource = new RoleResource();

        roleResource.setId( model.getId() );
        if ( model.getName() != null ) {
            roleResource.setName( model.getName().name() );
        }

        return roleResource;
    }

    @Override
    public void mapResourceToDomain(UserResource resource, User user) {
        if ( resource == null ) {
            return;
        }

        user.setId( resource.getId() );
        user.setFirstName( resource.getFirstName() );
        user.setLastName( resource.getLastName() );
        user.setEmail( resource.getEmail() );
        user.setLogin( resource.getLogin() );
        user.setPassword( resource.getPassword() );
        if ( user.getRoles() != null ) {
            List<RoleResource> list = resource.getRoles();
            if ( list != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( list );
            }
            else {
                user.setRoles( null );
            }
        }
        else {
            List<RoleResource> list = resource.getRoles();
            if ( list != null ) {
                user.setRoles( new HashSet<RoleResource>( list ) );
            }
        }
    }

    @Override
    public UserModel mapDomainToModel(User user) {
        if ( user == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setId( user.getId() );
        userModel.setFirstName( user.getFirstName() );
        userModel.setLastName( user.getLastName() );
        userModel.setEmail( user.getEmail() );
        userModel.setLogin( user.getLogin() );
        userModel.setPassword( user.getPassword() );
        userModel.setRoles( roleResourceSetToRoleModelSet( user.getRoles() ) );

        return userModel;
    }

    @Override
    public UserResource mapDomainToResource(User user) {
        if ( user == null ) {
            return null;
        }

        UserResource userResource = new UserResource();

        userResource.setId( user.getId() );
        userResource.setFirstName( user.getFirstName() );
        userResource.setLastName( user.getLastName() );
        userResource.setEmail( user.getEmail() );
        userResource.setLogin( user.getLogin() );
        userResource.setPassword( user.getPassword() );
        Set<RoleResource> set = user.getRoles();
        if ( set != null ) {
            userResource.setRoles( new ArrayList<RoleResource>( set ) );
        }
        else {
            userResource.setRoles( null );
        }

        return userResource;
    }

    protected Set<RoleResource> roleModelSetToRoleResourceSet(Set<RoleModel> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResource> set1 = new HashSet<RoleResource>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleModel roleModel : set ) {
            set1.add( toRole( roleModel ) );
        }

        return set1;
    }

    protected RoleModel roleResourceToRoleModel(RoleResource roleResource) {
        if ( roleResource == null ) {
            return null;
        }

        RoleModel roleModel = new RoleModel();

        roleModel.setId( roleResource.getId() );
        if ( roleResource.getName() != null ) {
            roleModel.setName( Enum.valueOf( RoleName.class, roleResource.getName() ) );
        }

        return roleModel;
    }

    protected Set<RoleModel> roleResourceSetToRoleModelSet(Set<RoleResource> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleModel> set1 = new HashSet<RoleModel>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleResource roleResource : set ) {
            set1.add( roleResourceToRoleModel( roleResource ) );
        }

        return set1;
    }
}
