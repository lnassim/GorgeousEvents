package RestServer.dataAccessObject;

import RestServer.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    RoleModel findByRoleName(String roleName);

    @Query("select role from Role role")
    Stream<RoleModel> getAllRolesStream();// Java8 Stream : on place la liste des rôles dans un Stream
}
