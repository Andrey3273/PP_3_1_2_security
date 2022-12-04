package com.example.springboot3.controller;

import com.example.springboot3.service.RoleServiceImpl;
import com.example.springboot3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.springboot3.entity.Role;
import com.example.springboot3.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    @Autowired
    public UserController(RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }
    @GetMapping(value = "/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userpage";
    }
    @GetMapping(value = "/admin")
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "all-user";
    }
    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "info";
    }
    @PostMapping(value = "/admin/add-user")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        roleServiceImpl.checkBoxRole(checkBoxRoles);
        user.setRoles(roleSet);
        userServiceImpl.addUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/{id}/edit")
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "edit";
    }
    @PatchMapping(value = "/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        roleServiceImpl.checkBoxRole(checkBoxRoles);
        user.setRoles(roleSet);
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
    @DeleteMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userServiceImpl.removeUserById(id);
        return "redirect:/admin";
    }
    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
}
