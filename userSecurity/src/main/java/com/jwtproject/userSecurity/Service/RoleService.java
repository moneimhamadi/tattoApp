package com.jwtproject.userSecurity.Service;

import org.springframework.stereotype.Service;

import com.jwtproject.userSecurity.Entity.Role;

@Service
public interface RoleService {
    Role saveRole(Role role);
    Role findByName(String name);
}
