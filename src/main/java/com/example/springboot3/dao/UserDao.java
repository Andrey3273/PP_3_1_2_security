package com.example.springboot3.dao;

import com.example.springboot3.entity.User;
import java.util.List;

public interface UserDao {
    void updateUser(User user);
    void removeUserById(long id);
    User getUserById(long id);
    List<User> getAllUsers();
    void addUser(User user);
    User getUserByName(String username);
}
