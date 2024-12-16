package vn.thietbidientu.service.impl;

import vn.thietbidientu.entity.Role;
import vn.thietbidientu.repository.RoleRepository;
import vn.thietbidientu.service.interfaces.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByRoleNames(List<String> roleNames) {
        return roleRepository.findByRoleNameIn(roleNames);
    }
}
