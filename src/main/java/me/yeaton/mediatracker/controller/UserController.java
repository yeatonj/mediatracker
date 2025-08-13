package me.yeaton.mediatracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.repository.UserRepository;

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
}
