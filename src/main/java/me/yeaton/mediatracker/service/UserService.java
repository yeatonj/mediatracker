package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.UserMain;
import me.yeaton.mediatracker.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create 
    public UserMain createUser(UserMain user) {
        return userRepository.save(user);
    }

    // Read
    public Iterable<UserMain> fetchUsers() {
        return userRepository.findAll();
    }

    // Update
    public UserMain updateUser(UserMain user, UUID id) {
        UserMain userDB = userRepository.findById(id).get();
        // update username
        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
            userDB.setUsername(user.getUsername());
        }
        // update email
        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            userDB.setEmail(user.getUsername());
        }
        // update password
        if (Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase(user.getPassword())) {
            userDB.setPassword(user.getPassword());
        }

        // update birthday
        if (Objects.nonNull(user.getBirthday())) {
            userDB.setBirthday(user.getBirthday());
        }
        // Can't update id, role, or createdAt, so we are done.
        return userRepository.save(userDB);
    } 

    // Delete
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
