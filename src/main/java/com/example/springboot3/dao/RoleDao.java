package com.example.springboot3.dao;

import com.example.springboot3.entity.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);
    void updateRole(Role role);
    void removeRoleById(long id);
    List<Role> getAllRoles();
    Role getRoleByName(String name);
}
