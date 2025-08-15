package me.yeaton.mediatracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create 
    @PostMapping
    public UserMain createUser(@RequestBody UserMain user) {
        return userService.createUser(user);
    }

    // Read
    @GetMapping
    public Iterable<UserMain> fetchUsers() {
        return userService.fetchUsers();
    }

    // Update
    @PutMapping("/{id}")
    public UserMain updateUser(@RequestBody UserMain user, @PathVariable("id") UUID id) {
        return userService.updateUser(user, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    // public UserController(UserRepository users) {
    //     this.users = users;
    // }

    // @GetMapping
    // public Iterable<UserMain> findAll() {
    //     return users.findAll();
    // }

    // @GetMapping("/{username}")
    // public UserMain findByUsernameQuery(@PathVariable String username) {
    //     return users.findByUsernameQuery(username);
    // }
    

    // @PostMapping
    // public ResponseEntity<Void> addUser(@RequestBody UserMain user) {
    //     try {
    //         users.save(new UserMain(user.getUsername(), user.getEmail(), user.getPassword()));
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
        
    //     return new ResponseEntity<>(HttpStatus.CREATED);
    // }

    // For the repository itself, note that we use:
    // save() to Create a new record
    // find() and variants to Read a record
    // findById() to get the object and then Update, they will automagically be persisted since these are managed objects
    // delete() and variants to Delete

}
