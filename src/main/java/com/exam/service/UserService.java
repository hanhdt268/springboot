package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;


    //get user by username
    public User getUser(String username);

    //get user by id
    public User getUserById(Long userId);

    //update user
    public User updateUser(User user) throws Exception;

    //get user by id
//    public User getUsers(Long userId);

    //delete user by id
    public void deleteById(Long userId);
}
