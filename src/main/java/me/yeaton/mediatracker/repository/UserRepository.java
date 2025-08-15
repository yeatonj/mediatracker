package me.yeaton.mediatracker.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.UserMain;

public interface UserRepository extends CrudRepository<UserMain, UUID> {
    List<UserMain> findByUsername(String username);
}
