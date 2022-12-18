package com.exam;

import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamProjectApplication implements CommandLineRunner {


    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ExamProjectApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting code");

    }
}
