package me.yeaton.mediatracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public Iterable<UserMain> findAll() {
        return users.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody UserMain user) {
        try {
            users.save(new UserMain(user.getUsername(), user.getEmail(), user.getRole(), user.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
