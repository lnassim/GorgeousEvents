package com.apiGorgeousEvent.Service;

import com.apiGorgeousEvent.DataAccessObject.RoleRepository;
import com.apiGorgeousEvent.Model.RoleModel;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class RoleServiceImpl implements RoleService {


    private RoleRepository roleRepository;

    // constructeur
    public RoleServiceImpl() {
        super();
    }
    @Autowired //autowired par constructeur pour bénéficier des tests unitaires
    public RoleServiceImpl(RoleRepository roleRepository) {
        super();
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<RoleModel> getAllRoles() {
        return IteratorUtils.toList(roleRepository.findAll().iterator());
    }

    @Override
    public Stream<RoleModel> getAllRolesStream() {
        return roleRepository.getAllRolesStream();
    }

    @Override
    public RoleModel findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}