package com.task.sber.controller;

import com.task.sber.annotation.CheckPermission;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @CheckPermission(access = {"test", "read"})
    @PostMapping("/login")
    public ResponseEntity<String> postLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(auth.getAuthorities().toString());
    }

//
//    @GetMapping("/test")
//    public ResponseEntity<String>
}
