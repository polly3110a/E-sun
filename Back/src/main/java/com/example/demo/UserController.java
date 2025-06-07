package com.example.demo;

import com.example.demo.UserRegisterRequest;
import com.example.demo.User;
import com.example.demo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.ok("註冊成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("註冊失敗: " + e.getMessage());
        }
    }
}
