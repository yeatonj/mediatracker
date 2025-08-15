package me.yeaton.mediatracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import me.yeaton.mediatracker.model.UserMain;

public interface UserRepository extends CrudRepository<UserMain, UUID> {
    Optional<UserMain> findByUsername(String username);

    @Query("SELECT username FROM user_main u WHERE u.username = :username")
    UserMain findByUsernameQuery(@Param("username") String username);
}
