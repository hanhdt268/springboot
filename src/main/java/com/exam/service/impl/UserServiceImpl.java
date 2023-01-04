package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());

        if (local != null) {
            System.out.println("User is already there!!");
            throw new Exception();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }

        return local;
    }


    //get user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public User updateUser(User user) throws Exception {
        return userRepository.save(user);
    }

//    @Override
//    public User getUsers(Long id) {
//        return userRepository.findById(id).get();
//    }

    @Override
    public void deleteById(Long userId) {
        this.userRepository.deleteById(userId);
    }


}
