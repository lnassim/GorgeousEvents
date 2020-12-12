package RestServer.service;

import RestServer.model.RoleModel;

import java.util.Collection;
import java.util.stream.Stream;

public interface RoleService {

    RoleModel findByRoleName(String roleName);

    Collection<RoleModel> getAllRoles();

    Stream<RoleModel> getAllRolesStream();
}
