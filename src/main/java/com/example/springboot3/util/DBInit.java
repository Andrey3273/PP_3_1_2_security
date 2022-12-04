package com.example.springboot3.util;

import com.example.springboot3.entity.Role;
import com.example.springboot3.entity.User;
import com.example.springboot3.service.RoleService;
import com.example.springboot3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final RoleService roleService;
    private final UserService userService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public DBInit(RoleService roleService, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.addRole(adminRole);
        roleService.addRole(userRole);
        Set<Role> roles_admin = new HashSet<>();
        roles_admin.add(roleService.getRoleByName("ROLE_ADMIN"));
        User admin = new User("Andrey", "Ivankov", "admin@mail.ru", "admin", roles_admin);
        userService.addUser(admin);
        Set<Role> roles_user = new HashSet<>();
        roles_user.add(roleService.getRoleByName("ROLE_USER"));
        User user = new User("Ivan", "Ivanov",
                "user@mail.ru", "user",  roles_user);
        userService.addUser(user);
    }
}
