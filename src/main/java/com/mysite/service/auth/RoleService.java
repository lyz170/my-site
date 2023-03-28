package com.mysite.service.auth;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Map<String, List<String[]>> buildRoleResourceMap();
}
