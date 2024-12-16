package vn.thietbidientu.service.interfaces;

import org.springframework.stereotype.Service;

import vn.thietbidientu.entity.Role;

import java.util.List;

@Service
public interface IRoleService {
    List<Role> findAll();

    List<Role> findByRoleNames(List<String> roleNames);
}
