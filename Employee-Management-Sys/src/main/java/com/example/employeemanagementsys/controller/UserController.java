package com.example.employeemanagementsys.controller;

import com.example.employeemanagementsys.dto.request.CreateUser;
import com.example.employeemanagementsys.dto.response.ApiResponse;
import com.example.employeemanagementsys.entity.User;
import com.example.employeemanagementsys.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@Valid @RequestBody CreateUser request) {
        try {
            User createdUser = userService.createUser(request);
            return ResponseEntity.status(201)
                    .body(ApiResponse.success(201, "Đăng ký người dùng thành công", createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(400)
                    .body(ApiResponse.error(400, e.getMessage()));
        }
    }
    @GetMapping("/hello")
    public static String getHello(){
        return "Hello Spring boot";
    }
}
