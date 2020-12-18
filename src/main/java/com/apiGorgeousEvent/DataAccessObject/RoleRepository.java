package com.apiGorgeousEvent.DataAccessObject;

import com.apiGorgeousEvent.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

// CELA AURAIT PERMIT DE DEFINIR SI C EST UN ADMIN OU UTILISATEUR
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    RoleModel findByRoleName(String roleName);

    @Query("select role from RoleModel role")
    Stream<RoleModel> getAllRolesStream();// Java8 Stream : on place la liste des rôles dans un Stream
}