package com.mysite.service.auth;


import com.mysite.entity.Role;
import com.mysite.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Map<String, List<String[]>> buildRoleResourceMap() {

        Map<String, List<String[]>> roleResourceMap = new HashMap<>();
        List<Role> roleList = roleRepository.findAll();
        if (!CollectionUtils.isEmpty(roleList)) {
            roleList.forEach(role -> {
                if (!CollectionUtils.isEmpty(role.getResources())) {
                    String roleName = role.getRoleName();
                    List<String[]> resourceList = new ArrayList<>();
                    role.getResources().forEach(resource -> resourceList.add(new String[]{resource.getMethod(), resource.getPath()}));
                    roleResourceMap.put(roleName, resourceList);
                }
            });
        }

        return roleResourceMap;
    }
}
