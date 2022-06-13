package be.intec.newreserveblog.service.Impl;

import be.intec.newreserveblog.entity.Role;
import be.intec.newreserveblog.repository.RoleRepository;
import be.intec.newreserveblog.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public void updateRole(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
