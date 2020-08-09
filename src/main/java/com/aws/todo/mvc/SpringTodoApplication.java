package com.aws.todo.mvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringTodoApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringTodoApplication.class, args);
    }
}
