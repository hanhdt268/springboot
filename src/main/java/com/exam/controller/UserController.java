package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //creating user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {

        user.setProfile("https://www.bootdey.com/img/Content/avatar/avatar7.png");
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        roles.add(userRole);
        return ResponseEntity.ok(this.userService.createUser(user, roles));
    }


    //find username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    //delete user by id

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId) {
        this.userService.deleteById(userId);
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<?> testControllerCmd(@PathVariable("name") String name) {
        String s = "Hello World %s !!!";
        String str = String.format(s, name);
        System.out.println("a: " + str);

        return ResponseEntity.ok(str);
    }
}
