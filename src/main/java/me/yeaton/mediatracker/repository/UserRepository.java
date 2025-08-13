package me.yeaton.mediatracker.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import me.yeaton.mediatracker.model.UserMain;

public interface UserRepository extends CrudRepository<UserMain, UUID> {

}
