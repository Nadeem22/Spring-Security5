package com.nadeem.spring.security.jdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

        @GetMapping("/")
        public String getWelcomeMessage(){
            return "Welcome";
        }
        @GetMapping("/user")
        public String getUser(){
            return "user";
        }
        @GetMapping("/admin")
        public String getAdmin(){
            return "admin";
        }
}
