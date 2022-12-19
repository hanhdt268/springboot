package com.exam;

import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExamProjectApplication implements CommandLineRunner {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ExamProjectApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting code");

//            User user = new User();
//
//            user.setFirstName("Hanh");
//            user.setLastName("Nguyen");
//            user.setUsername("hanh88");
//            user.setPassword(this.bCryptPasswordEncoder.encode("123"));
//            user.setEmail("hanh@gmail.com");
//            user.setPhone("1231241123");
//            user.setProfile("https://www.bootdey.com/img/Content/avatar/avatar7.png");
//
//            Role role1 = new Role();
//            role1.setRoleId(44L);
//            role1.setRoleName("Admin");
//
//            Set<UserRole> userRoles = new HashSet<>();
//            UserRole userRole = new UserRole();
//            userRole.setRole(role1);
//            userRole.setUser(user);
//
//            userRoles.add(userRole);
//
//            User user1 = this.userService.createUser(user, userRoles);
//            System.out.println(user1);

    }
}
