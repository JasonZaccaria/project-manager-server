package com.projectmanager.projectmanagerproject.role;

import java.util.List;

public interface RoleService {
    Role getRole(String roleName);

    List<Role> getRoles();

    Role saveRole(Role role);
}
