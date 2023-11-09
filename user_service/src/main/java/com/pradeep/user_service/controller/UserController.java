package com.pradeep.user_service.controller;

import com.pradeep.user_service.entities.User;
import com.pradeep.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    //create
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user1);
    }

    //single user get
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        User user1 = service.getUser(userId);
        return ResponseEntity.ok(user1);
    }

    //all user
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user1 = service.getAllUser();
        return ResponseEntity.ok(user1);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        service.deleteUser(userId);
        return ResponseEntity.ok("User with id "+userId+" deleted successfully.");
    }

    //update
    @PostMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId,@RequestBody User user) {
        User user1=service.updateUser(userId,user);
        return ResponseEntity.ok(user1);
    }


}
